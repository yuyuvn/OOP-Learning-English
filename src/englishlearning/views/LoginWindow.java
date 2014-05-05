/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.presenter.Presenter;
import englishlearning.views.Controller.FXMLPath;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 */
@FXMLPath("Window.fxml")
public class LoginWindow extends Window {
    //<editor-fold defaultstate="collapsed" desc="Property login">
    private ReadOnlyObjectWrapper<Login> login;
    
    public final Login getLogin() {
        return loginProperty().get();
    }
    
    public final ReadOnlyObjectWrapper<Login> loginProperty() {
        if (login == null) login = new ReadOnlyObjectWrapper<>(this, "login", new Login());
        return login;
    }
//</editor-fold>
        
    public LoginWindow(Stage stage, Presenter presenter) {
        super(stage, presenter);
        
        setMinWidth(400);
        setMinHeight(300);
                
        this.setTitle("VOA English Learning - Login");
        this.setContent(getLogin());
        this.showWindow();
    }
}
