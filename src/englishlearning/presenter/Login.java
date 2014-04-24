/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Clicia
 */
public class Login extends Presenter {
    @FXML Button loginButton;
    @FXML TextField username;
    
    public Login() {
        loadFXML();
    }
    
    public final void setOnLogin(EventHandler<ActionEvent> onLogin) {
        loginButton.addEventHandler(ActionEvent.ACTION, onLogin);
    }
    
    public String getUsername() {
        return username.getText();
    }
}