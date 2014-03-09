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
import javafx.fxml.FXML;
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

    public LoginView(P presenter) {
        super(presenter);
    }

    @Override
    protected String getViewPath() {
        return "/resource/fxml/LoginView.fxml"; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setLabel(String text) {
        System.out.println("You clicked me!");
        label.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
        
    @FXML
    @Override
    public void handleButtonAction(ActionEvent event) {        
        this.getPresenter().handleButtonAction(event);
    }
}