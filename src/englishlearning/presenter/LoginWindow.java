/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.presenter.Controller.FXMLPath;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 */
@FXMLPath("Window.fxml")
public class LoginWindow extends Window {
    
    public LoginWindow(Stage stage) {
        super(stage);
        
        setMinWidth(400);
        setMinHeight(300);
        
        Login login = new Login();
        login.isLogedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("User " + login.getUsername() + " logged");

                MainWindow mainWindow = new MainWindow(new Stage(), login.getUsername());
                this.closeWindow();
            }
        });
        
        this.setTitle("VOA English Learning - Login");
        this.setContent(login);
        this.showWindow();
    }
}
