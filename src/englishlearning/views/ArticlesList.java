/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.views.Controller;
import englishlearning.controls.ListViewEx;
import englishlearning.model.model.IArticle;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.model.property.WrapperProperty;
import englishlearning.util.DataInNet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import org.controlsfx.control.textfield.CustomTextField;

/**
 *
 * @author Clicia
 */
public class ArticlesList extends Controller {   
    @FXML private CustomTextField searchField;
    @FXML public ListViewEx listView;
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
    
    public final WrapperProperty<IArticle> selectedArticleProperty() {
        if (selectedArticle == null) {            
            selectedArticle = new WrapperProperty<>(this, "selectedArticle");
        }
        return selectedArticle;
    }
//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Property selected">
    private BooleanProperty selected;

    public final void fireSelected() {
        BooleanProperty property = selectedProperty();
        property.set(!property.get());
    }

    public BooleanProperty selectedProperty() {
        if (selected == null) selected = new SimpleBooleanProperty(this, "selected", false);
        return selected;
    }
//</editor-fold>
    
    public ArticlesList() {
        getData();
        selectedArticleProperty().bind(listView.getSelectionModel().selectedItemProperty());
        listView.selectedProperty().addListener((e) -> {
            fireSelected();
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
    
    public void clearSelection() {
        listView.getSelectionModel().clearSelection();
    }
}
