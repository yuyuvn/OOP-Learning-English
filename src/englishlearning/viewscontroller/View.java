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
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Region;
/**
 *
 * @author Clicia
 */
public abstract class View extends Region {     
    private final String resourcePath = "/resource/fxml/%s.fxml";
 
    public View() {
        this.loadFXML();
    }
 
    private void loadFXML() {
        FXMLLoader loader = new FXMLLoader();

        loader.setController(this);
        loader.setLocation(this.getViewURL());
 
        try {
            Node root = (Node) loader.load();
            setMaxSize(root);
            this.getChildren().add(root);
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
}
