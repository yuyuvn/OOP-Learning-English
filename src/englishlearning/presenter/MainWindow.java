/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.presenter.Controller.FXMLPath;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 */
@FXMLPath("Window.fxml")
public class MainWindow extends Window {
    
    public MainWindow(Stage stage, String username) {
        super(stage);
        
        setMinWidth(900);
        setMinHeight(600);
        
        MainContent mainContent = new MainContent();
        mainContent.setUser(username);
        
        this.setTitle("VOA Learning English");
        this.setContent(mainContent);
        this.showWindow();
    }
    
}
