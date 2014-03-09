/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.viewscontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import java.io.IOException;
import java.util.logging.*;
import java.net.URL;
/**
 *
 * @author Clicia
 */
public abstract class UserControl extends Region {     
    private final String resourcePath = "%s.fxml";
 
    public UserControl() {
        this.loadFXML();
    }
 
    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader();
 
        loader.setController(this);
        loader.setLocation(this.getViewURL());
 
        try {
            Node root = (Node) loader.load();
            this.getChildren().add(root);
        }
        catch (IOException ex) {
            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
 
    /**
     *
     * @return
     */
    protected String getViewPath() {
        return String.format(resourcePath, this.getClass().getSimpleName());
    }
 
    private URL getViewURL() {
        return this.getClass().getResource(this.getViewPath());
    }
}
