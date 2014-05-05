/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.model.IArticle;
import englishlearning.model.model.IUser;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.UserWrapper;
import englishlearning.views.ArticlesList;
import englishlearning.views.MainContent;
import englishlearning.views.MainWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
    
}
