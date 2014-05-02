/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URL;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author Clicia
 */
public abstract class Controller extends AnchorPane {     
    private final String __resourceDir = "/englishlearning/views/fxml/";

    public Controller() {
        loadFXML();
    }
    
    protected final void loadFXML() {
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
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
 
    private String getViewPath() {
        return String.format(__resourceDir + "%s.fxml", this.getClass().getSimpleName());
    }
 
    private URL getViewURL() {
        String path = this.getViewPath();
        for (FXMLPath annotation : this.getClass().getAnnotationsByType(FXMLPath.class)) {
            if (annotation.fullPath()) {
                path = annotation.value();
            } else {
                path = __resourceDir + annotation.value();
            }
        }
        return this.getClass().getResource(path);
    }
    
    /**
     * Set FXML file Path, if not set, it will use class name as default
     * Option property fullPath: 
     *      if true then use value as full path
     *      ex: @FXMLPath(value="/resource/fxml/controller.fxml",fullPath=true)
     */
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface FXMLPath {
        String value();
        boolean fullPath() default false;
    }
}
