/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.model.IArticle;
import englishlearning.model.model.IUser;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.model.wrapper.UserWrapper;
import englishlearning.util.DataInNet;
import englishlearning.views.ArticlesList;
import englishlearning.views.MainContent;
import englishlearning.views.MainWindow;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

/**
 *
 * @author Clicia
 * @param <V>
 */
public class MainPresenter<V extends MainWindow> extends Presenter<V> {
    //<editor-fold defaultstate="collapsed" desc="user">
    private WrapperProperty<IUser> user;
    public final IUser getUser() { return userProperty().get(); }
    public final void setUser(IUser value) { userProperty().set(value); }
    public final WrapperProperty<IUser> userProperty() {
        if (user == null) user = new WrapperProperty(this, "user", new UserWrapper());
        return user;
    }
//</editor-fold>
    
    public MainPresenter(V view) {
        super(view);
    }

    public MainPresenter() {
        super();
    }
    
    @Override
    protected void initialize() {
        setArticlesListData();
        getView().userProperty().bindBidirectional(userProperty());
        MainContent mainContent = getView().getMainContent();
        ArticlesList articlesList = mainContent.getArticlesList();

        mainContent.setOnReturn(e -> mainContent.setData(getUser()));        
        articlesList.selectedProperty().addListener(e -> {
            IArticle article = articlesList.getSelectedArticle();
            if (article != null) {
                getUser().getUser().getReadList().putIfAbsent(article.getArticle().getGuid(), 0.0);
                mainContent.setData(article);
                // Don't know why need to fire 2 times.
                // bug some where or java sync like shit?
                getUser().getProperty().fireValueChangedEvent();
                getUser().getProperty().fireValueChangedEvent();
            }
        });
        
        userProperty().addListener((ObservableValue<? extends IUser> observable, IUser oldValue, IUser newValue) -> {
            if (newValue.getUser().getPlayState() == null)
                mainContent.setData(getUser());
            else {
                // TODO
            }
        });
        
        userProperty().fireValueChangedEvent();
    }
    
    private void setArticlesListData() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task<Collection<IArticle>> task = new Task<Collection<IArticle>>() {
            @Override
            protected Collection<IArticle> call() throws Exception {
                return DataInNet.getListArticle().stream()
                    .flatMap(a -> Stream.of(new ArticleWrapper(a)))
                    .collect(Collectors.toList());
            }
        };
        
        task.valueProperty().addListener(t -> {
            getView().getMainContent().getArticlesList().setArticles(task.getValue());
            executor.shutdown();
        });
        
        executor.submit(task);
    }
}
