/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package englishlearning.viewscontroller;

import englishlearning.presenter.LoginPresenter;
import englishlearning.views.ILoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.controlsfx.control.HyperlinkLabel;
import org.controlsfx.control.PopOver;

/**
*
* @author Clicia
 * @param <P>
*/
public class LoginView<P extends LoginPresenter> extends ViewController<P> implements ILoginView
{
    
    @FXML
    private HyperlinkLabel label;
    @FXML
    private Button button;
    @FXML
    private PopOver popOver;

    public LoginView(P presenter) {
        super(presenter);
    }

    @Override
    protected String getViewPath() {
        return "/resource/fxml/LoginView.fxml";
    }
    
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }*/
    
    @Override
    public HyperlinkLabel getLabel() {
        return this.label;
    }
    @Override
    public Button getButton() {
        return this.button;
    }

    @Override
    public PopOver getPopOver() {
        return this.popOver;
    }

    @Override
    public void setPopOver(PopOver popOver) {
        this.popOver = popOver;
    }
}