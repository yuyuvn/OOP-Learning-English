/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.model.IUser;
import englishlearning.model.wrapper.WrapperProperty;

/**
 *
 * @author Clicia
 */
public class MainContent extends Controller {    
    //<editor-fold defaultstate="collapsed" desc="Property User">
    private WrapperProperty<IUser> user;
    public final IUser getUser() { return userProperty().get(); }
    protected final void setUser(IUser value) { userProperty().set(value); }
    public final WrapperProperty<IUser> userProperty() { 
        if (user == null) user = new WrapperProperty(this, "user");
        return user; 
    }
//</editor-fold>    
    
}
