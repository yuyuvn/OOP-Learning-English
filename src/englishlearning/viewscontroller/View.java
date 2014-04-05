/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.viewscontroller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author Clicia
 */
public abstract class View extends AnchorPane {     
    private final String resourcePath = "/resource/fxml/%s.fxml";
 
    public View() {
        this.loadFXML();
    }
 
    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader();

        loader.setRoot(this);
        loader.setController(this);
        loader.setLocation(this.getViewURL());
 
        try {
            loader.load();
            AnchorPane.setBottomAnchor(this, .0);
            AnchorPane.setLeftAnchor(this, .0);
            AnchorPane.setRightAnchor(this, .0);
            AnchorPane.setTopAnchor(this, .0);
        }
        catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
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
