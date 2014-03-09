/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package englishlearning.viewscontroller;

import englishlearning.presenter.LoginPresenter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
*
* @author Clicia
*/
public class LoginViewController<P extends LoginPresenter> extends ViewController<P> implements Initializable
{
    
    @FXML
    private Label label;

    public LoginViewController(P presenter) {
        super(presenter);
    }

    @Override
    protected String getViewPath() {
        return "/englishlearning/views/LoginView.fxml"; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setLabel(String text) {
        System.out.println("You clicked me!");
        label.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
        
    @FXML
    public void handleButtonAction(ActionEvent event) {        
        this.getPresenter().handleButtonAction(event);
    }
}