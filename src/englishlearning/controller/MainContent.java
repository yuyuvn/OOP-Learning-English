/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controller;

import englishlearning.model.UserInfo;
import englishlearning.model.builder.UserInfoBuilder;
import englishlearning.model.wrapper.UserWrapper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Clicia
 */
public class MainContent extends Controller {
    
    //<editor-fold defaultstate="collapsed" desc="Property User">
    private ObjectProperty<UserWrapper> user;
    public final UserWrapper getUser() { return userProperty().get(); }
    protected final void setUser(UserWrapper value) { userProperty().set(value); }
    public final ObjectProperty<UserWrapper> userProperty() { 
        if (user == null) {
             user = new SimpleObjectProperty<>(this, "user");
        }
        return user; 
    }
//</editor-fold>
    
    public UserInfo setUser(String username) {
        UserInfo u = new UserInfo();
        u.name = username;
        UserWrapper wp = new UserWrapper(UserInfoBuilder.create().name(username).build());
        // TODO: get user info from username
        setUser(wp);
        return u;
    }
}
