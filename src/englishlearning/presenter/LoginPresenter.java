/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.UsersList;
import englishlearning.views.ILoginView;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Clicia
 * @param <V>
 * @param <M>
 */
public class LoginPresenter<V extends ILoginView, M extends UsersList> extends Presenter<V,M> {    
    @Override
    protected void initialize() {
        TextFields.bindAutoCompletion(getView().getUsername(), Collections.synchronizedMap(getModel()));
        getView().getLoginButton().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            // getModel().saveData(); // TODO: add datapath
        });
    }
    
    public final void setOnLogin(EventHandler<ActionEvent> onLogin) {
        getView().getLoginButton().addEventHandler(ActionEvent.ACTION, onLogin);
    }
    
    public String getName() {
        return getModel().getUsername();
    }
}
