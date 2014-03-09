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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
*
* @author Clicia
 * @param <P>
*/
public class LoginView<P extends LoginPresenter> extends ViewController<P> implements ILoginView
{
    
    @FXML
    private Label label;
    @FXML
    private Button button;

    public LoginView(P presenter) {
        super(presenter);
    }

    @Override
    protected String getViewPath() {
        return "/resource/fxml/LoginView.fxml"; //To change body of generated methods, choose Tools | Templates.
    }
    
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }*/
    
    @Override
    public Label getLabel() {
        return this.label;
    }
    @Override
    public Button getButton() {
        return this.button;
    }
}