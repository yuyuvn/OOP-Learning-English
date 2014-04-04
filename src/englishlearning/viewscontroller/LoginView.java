/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package englishlearning.viewscontroller;

import englishlearning.presenter.LoginPresenter;
import englishlearning.views.ILoginView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
*
* @author Clicia
 * @param <P>
*/
public class LoginView<P extends LoginPresenter> extends ViewController<P> 
    implements ILoginView
{
    @FXML
    private TextField username;
    @FXML
    private Button loginButton;
    
    public LoginView(P presenter) {
        super(presenter);
    }

    @Override
    public TextField getUsername() {
        return username;
    }

    @Override
    public Button getLoginButton() {
        return loginButton;
    }
}