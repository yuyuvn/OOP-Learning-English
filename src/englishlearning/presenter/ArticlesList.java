/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.controls.Loading;
import englishlearning.model.model.IArticle;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.util.DataInNet;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
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
    //<editor-fold defaultstate="collapsed" desc="Property isLoading">
    private BooleanProperty loading;
    
    public final boolean isLoading() {
        return loadingProperty().get();
    }
    
    public final void setLoading(boolean value) {
        loadingProperty().set(value);
    }
    
    public final BooleanProperty loadingProperty() {
        if (loading == null) loading = new SimpleBooleanProperty(this,"loading",true);
        return loading;
    }
//</editor-fold> 
    //<editor-fold defaultstate="collapsed" desc="Property articles">
    private ListProperty<IArticle> articles;
    
    public ObservableList<IArticle> getArticles() {
        return articlesProperty().get();
    }
    
    public void setArticles(ObservableList<IArticle> value) {
        articlesProperty().set(value);
    }
    
    public ListProperty<IArticle> articlesProperty() {
        if (articles == null) articles = new SimpleListProperty<>(this, "articles");
        return articles;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property notLoading">
    private ReadOnlyBooleanWrapper notLoading;
    
    public boolean isNotLoading() {
        return !notLoadingProperty().get();
    }
    
    public ReadOnlyBooleanProperty notLoadingProperty() {
        if (notLoading == null) {
            notLoading = new ReadOnlyBooleanWrapper(this, "notLoading");
            notLoading.bind(loadingProperty());
        }
        return notLoading;
    }
//</editor-fold>
    
    
    public ArticlesList() {
        getData();
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
            setLoading(false);
        });
        
        executor.submit(task);
    }
}
