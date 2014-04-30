/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controller;

import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Clicia
 */
public class Window extends Controller {    
    // <editor-fold desc="Property content" defaultstate="collapsed">
    private ObjectProperty<Controller> content;
    public final Controller getContent() { return contentProperty().get(); }
    public final void setContent(Controller value) { contentProperty().set(value); }
    public final ObjectProperty<Controller> contentProperty() { 
        if (content == null) {
            content = new SimpleObjectProperty<>(this, "content");
        }
        return content;
    }
    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property title">
    private StringProperty title;
    public final String getTitle() { return titleProperty().get(); }
    public final void setTitle(String value) { titleProperty().set(value); }
    public final StringProperty titleProperty() {
        if (title == null) {
            title = new SimpleStringProperty(this, "title", "");
        }
        return title; 
    }
//</editor-fold>
    
    private final Stage stage;
    
    public Window(Stage stage) {
        super();
        this.stage = stage;
    }
    
    public void showWindow() {
        stage.setTitle(getTitle());
        UndecoratorScene undecoratorScene = new UndecoratorScene(stage, this);
        
        stage.setScene(undecoratorScene);
        stage.sizeToScene();
        stage.toFront();
                
        Undecorator undecorator = undecoratorScene.getUndecorator();
        stage.setMinWidth(undecorator.getMinWidth());
        stage.setMinHeight(undecorator.getMinHeight());
        
        stage.show();
    }
    
    public void closeWindow() {
        stage.close();
    }
}
