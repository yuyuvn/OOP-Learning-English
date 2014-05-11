/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.model.IArticle;
import englishlearning.model.property.ReadOnlyWrapper;
import englishlearning.model.property.ReadOnlyWrapperProperty;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.util.DataInNet;
import englishlearning.views.ArticlesList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

/**
 *
 * @author Clicia
 * @param <V>
 */
public class ArticlesListPresenter<V extends ArticlesList> extends Presenter<V> {
    //<editor-fold defaultstate="collapsed" desc="Property selectedArticle">    
    public final IArticle getSelectedArticle() {
        return selectedArticleProperty().get();
    }
    
    public final ReadOnlyWrapperProperty<IArticle> selectedArticleProperty() {
        return getView().selectedArticleProperty();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property selected">
    public BooleanProperty selectedProperty() {
        return getView().selectedProperty();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property exception">
    private ReadOnlyObjectWrapper<Throwable> exception;
    
    public Throwable getException() {
        return _exceptionProperty().get();
    }
    
    private void setException(Throwable value) {
        _exceptionProperty().set(value);
    }
    
    public ReadOnlyObjectProperty<Throwable> exceptionProperty() {
        return _exceptionProperty().getReadOnlyProperty();
    }
    
    private ReadOnlyObjectWrapper<Throwable> _exceptionProperty() {
        if (exception == null) exception = new ReadOnlyObjectWrapper<>(this, "exception");
        return exception;
    }
//</editor-fold>
    
    
    private Collection<IArticle> articles = null;

    public ArticlesListPresenter() {
    }

    public ArticlesListPresenter(V view) {
        super(view);
    }
    
    @Override
    protected void initialize() {
        // filter articles
        getView().filterTextProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null && !newValue.equals("")) {
                if (oldValue != null && !oldValue.equals("") && newValue.contains(oldValue)) {
                    getView().setArticles(
                            getView().getArticles()
                                    .stream().filter(
                                            a -> a.getArticle().getTitle().toLowerCase().contains(
                                                    newValue.toLowerCase())).collect(Collectors.toList())
                    );
                } else {
                    getView().setArticles(
                                articles.stream()
                                        .filter(a -> a.getArticle().getTitle().toLowerCase().contains(newValue.toLowerCase()))
                                        .collect(Collectors.toList()));
                }
            } else {
                getView().setArticles(articles);
            }   
            getView().clearSelection();            
        });
        
        
    }
    
    private void setArticlesListData() {
        if (articles != null) return;
        ExecutorService ect = Executors.newCachedThreadPool();
        Task<Collection<IArticle>> task = new Task<Collection<IArticle>>() {
            @Override
            protected Collection<IArticle> call() throws Exception {
                return DataInNet.getListArticle().stream()
                    .flatMap(a -> Stream.of(new ArticleWrapper(a)))
                    .collect(Collectors.toList());
            }
        };
        
        task.exceptionProperty().addListener((ObservableValue<? extends Throwable> observable, Throwable oldValue, Throwable newValue) -> {
            setException(newValue);
            ect.shutdown();
        });
        
        task.valueProperty().addListener(t -> {
            articles = task.getValue();
            getView().setArticles(articles);
            ect.shutdown();
        });
        
        ect.submit(task);
    }
    
    public void reset() {
        setArticlesListData();        
        getView().setFilterText("");   
        getView().clearSelection();
    }
}
