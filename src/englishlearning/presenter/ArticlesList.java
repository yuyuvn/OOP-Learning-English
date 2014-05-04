/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.controls.ListViewEx;
import englishlearning.model.model.IArticle;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.model.wrapper.WrapperProperty;
import englishlearning.util.DataInNet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.controlsfx.control.textfield.CustomTextField;

/**
 *
 * @author Clicia
 */
public class ArticlesList extends Controller {   
    @FXML private CustomTextField searchField;
    @FXML private ListViewEx listView;
    //<editor-fold defaultstate="collapsed" desc="Property articles">
    private ListProperty<IArticle> articles;
    
    public ObservableList<IArticle> getArticles() {
        return articlesProperty().get();
    }
    
    private void setArticles(ObservableList<IArticle> value) {
        articlesProperty().set(value);
    }
    
    public ListProperty<IArticle> articlesProperty() {
        if (articles == null) articles = new SimpleListProperty<>(this, "articles");
        return articles;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property selectedArticle">
    private WrapperProperty<IArticle> selectedArticle;
    
    public IArticle getSelectedArticle() {
        return selectedArticleProperty().get();
    }
    
    private void setSelectedArticle(IArticle value) {
        selectedArticleProperty().set(value);
    }
    
    public WrapperProperty<IArticle> selectedArticleProperty() {
        if (selectedArticle == null) {            
            selectedArticle = new WrapperProperty<>(this, "selectedArticle");
        }
        return selectedArticle;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property readArticle">
    private WrapperProperty<IArticle> readArticle;
    
    public IArticle getReadArticle() {
        return readArticle.get();
    }
    
    public void setReadArticle(IArticle value) {
        readArticle.set(value);
    }
    
    public WrapperProperty<IArticle> readArticleProperty() {
        if (readArticle == null) readArticle = new WrapperProperty<>(this, "readArticle");
        return readArticle;
    }
//</editor-fold>
    
    
    public ArticlesList() {
        getData();
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IArticle>() {

            @Override
            public void changed(ObservableValue<? extends IArticle> observable, IArticle oldValue, IArticle newValue) {
                setSelectedArticle(newValue);
            }
        });
        listView.selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (newValue instanceof IArticle) {
                setReadArticle((IArticle)newValue);
                setSelectedArticle(null);
            }
        });
    }
    
    private void getData() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task<ObservableList<IArticle>> task = new Task<ObservableList<IArticle>>() {
            @Override
            protected ObservableList<IArticle> call() throws Exception {
                return FXCollections.observableArrayList(DataInNet.getListArticle().stream()
                    .flatMap(a -> Stream.of(new ArticleWrapper(a)))
                    .collect(Collectors.toList()));
            }            
        };
        
        task.valueProperty().addListener(t -> {
            setArticles(task.getValue());
        });
        
        executor.submit(task);
    }
}
