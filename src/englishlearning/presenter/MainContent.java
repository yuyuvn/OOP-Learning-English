/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.UserInfo;
import englishlearning.model.UserInfoBuilder;
import englishlearning.model.wrapper.UserWrapper;
import englishlearning.model.wrapper.WrapperProperty;

/**
 *
 * @author Clicia
 */
public class MainContent extends Controller {
    
    //<editor-fold defaultstate="collapsed" desc="Property User">
    private WrapperProperty<UserWrapper<UserInfo>> user;
    public final UserWrapper<UserInfo> getUser() { return userProperty().get(); }
    protected final void setUser(UserWrapper<UserInfo> value) { userProperty().set(value); }
    public final WrapperProperty<UserWrapper<UserInfo>> userProperty() { 
        if (user == null) user = new WrapperProperty(this, "user");
        return user; 
    }
    public void raiseUserPropertyChange() {
        userProperty().fireValueChangedEvent();
    }
//</editor-fold>
    
    public UserInfo setUser(String username) {
        UserInfo u = UserInfoBuilder.create().name(username).build();
        UserWrapper wp = new UserWrapper(UserInfoBuilder.create().name(username).build());
        // TODO: get user info from username
        setUser(wp);
        return u;
    }
}
