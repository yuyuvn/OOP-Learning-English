/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IUser;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.UserWrapper;

/**
 *
 * @author Clicia
 */
public class UserInfoBox extends Controller implements DataReceivable {

    //<editor-fold defaultstate="collapsed" desc="IUser User">
    private WrapperProperty<IUser> data;
    public final IUser getData() { return dataProperty().get(); }
    public final void setData(IUser value) { dataProperty().set(value); }
    public final WrapperProperty<IUser> dataProperty() { 
        if (data == null) data = new WrapperProperty(this, "data", new UserWrapper());
        return data;
    }
//</editor-fold>
        
    @Override
    public void setData(Object value) {
        try {
            setData((IUser)value);
        } catch (ClassCastException e) {
        }
    }
    
}
