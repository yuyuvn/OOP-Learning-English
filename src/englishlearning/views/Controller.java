/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URL;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
/**
 *
 * @author Clicia
 */
public abstract class Controller extends Region {     
    private final String __resourceDir = "/englishlearning/views/fxml/";
    private final String __cssDir = "/englishlearning/views/css/";

    public Controller() {
        loadFXML();
    }
    
    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader();

        //loader.setRoot(this);
        loader.setController(this);
        loader.setLocation(this.getViewURL());
 
        try {
            Node root = (Node) loader.load();
            setMaxSize(root);
            this.getChildren().add(root);
            try {
                this.getStylesheets().add(getCSSUrl().toExternalForm());
            } catch (Exception e) {
                // css may not exist, never mind it
            }
            AnchorPane.setBottomAnchor(this, .0);
            AnchorPane.setLeftAnchor(this, .0);
            AnchorPane.setRightAnchor(this, .0);
            AnchorPane.setTopAnchor(this, .0);
        }
        catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
 
    private String getPath(String path, String exp) {
        return String.format(path + "%s" + exp, this.getClass().getSimpleName());
    }
 
    private URL getViewURL() {
        String path = this.getPath(__resourceDir, ".fxml");
        for (FXMLPath annotation : this.getClass().getAnnotationsByType(FXMLPath.class)) {
            if (annotation.fullPath()) {
                path = annotation.value();
            } else {
                path = __resourceDir + annotation.value();
            }
        }
        return this.getClass().getResource(path);
    }
    
    private URL getCSSUrl() {
        String path = this.getPath(__cssDir, ".css");        
        return this.getClass().getResource(path);
    }
    
    @Override
    protected void layoutChildren() {
        getChildren().stream().forEach((node) -> {
            layoutInArea(node, 0, 0, getWidth(), getHeight(), 0, HPos.LEFT, VPos.TOP);
        });
    }
    
    private void setMaxSize(Node node) {
        if (node != null && node instanceof Region) {
            Region region = (Region) node;
            region.setMaxWidth(Double.MAX_VALUE);
            region.setMaxHeight(Double.MAX_VALUE);
        }
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
