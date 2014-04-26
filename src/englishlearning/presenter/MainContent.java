/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.UserInfo;
import englishlearning.model.wrapper.UserWrapper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Clicia
 */
public class MainContent extends Presenter {
    
    //<editor-fold defaultstate="collapsed" desc="Property User">
    private final ObjectProperty<UserWrapper> user = new SimpleObjectProperty<>(this, "user");;
    public final UserWrapper getUser() { return user.get(); }
    protected final void setUser(UserWrapper value) { user.set(value); }
    public final ObjectProperty<UserWrapper> userProperty() { return user; }
//</editor-fold>
    
    public MainContent() {
        loadFXML();
    }
    
    public UserInfo setUser(String username) {
        UserInfo u = new UserInfo();
        u.name = username;
        UserWrapper wp = new UserWrapper(u);
        // TODO: get user info from username
        setUser(wp);
        return u;
    }
}
