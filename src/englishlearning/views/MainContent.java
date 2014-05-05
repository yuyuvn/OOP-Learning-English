/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IArticle;
import englishlearning.model.model.IUser;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.UserWrapper;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

/**
 *
 * @author Clicia
 */
public class MainContent extends Controller {
    @FXML ArticlesList articlesList;
    public ArticlesList getArticlesList() {return articlesList;}
    
    //<editor-fold defaultstate="collapsed" desc="Property User">
    private WrapperProperty<IUser> user;
    public final IUser getUser() { return userProperty().get(); }
    protected final void setUser(IUser value) { userProperty().set(value); }
    public final WrapperProperty<IUser> userProperty() { 
        if (user == null) user = new WrapperProperty(this, "user", new UserWrapper());
        return user; 
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property data">
    private ObjectProperty data;
    
    public final Object getData() {
        return dataProperty().get();
    }
    
    public final void setData(Object value) {
        dataProperty().set(value);
    }
    
    public final ObjectProperty dataProperty() {
        if (data == null) data = new SimpleObjectProperty(this, "data");
        return data;
    }
//</editor-fold>
    
    @FXML
    private EventHandler onReturn;
    public void setOnReturn(EventHandler eventHandler) {
        onReturn = eventHandler;
    }
    
    public void onReturn(ActionEvent event) {
        setData(getUser());
    }
}
