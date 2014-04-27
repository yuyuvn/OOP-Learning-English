/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.util.WindowsBehavior;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Clicia
 */
public class MainWindow extends Presenter {
    @FXML
    private Pane contains;
    
    // <editor-fold desc="Property content" defaultstate="collapsed">
    private final ObjectProperty<Presenter> content = new SimpleObjectProperty<>(this, "content");;
    public final Presenter getContent() { return content.get(); }
    public final void setContent(Presenter value) { 
        if (getContent()!=value) {
            contains.getChildren().clear();
            contains.getChildren().add(value);
            content.set(value);
        }
    }
    public final ObjectProperty<Presenter> contentProperty() { return content; }
    // </editor-fold>
    
    private MainContent mainContent;
    
    public MainWindow() {
        loadFXML();
        
        Login login = new Login();
        login.isLogedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                System.out.println("User " + login.getUsername() + " logged");
                mainContent = new MainContent();
                mainContent.setUser(login.getUsername());
                
                WindowsBehavior.setWindowSize(this.getScene().getWindow(), 960.0, 640.0);
                
                setContent(mainContent);
            }
        });
        setContent(login);
    }
    
    public void showWindows(Stage stage) {
        stage.setTitle("VOA Learning English");
        UndecoratorScene undecoratorScene = new UndecoratorScene(stage, this);
        
        stage.setScene(undecoratorScene);
        stage.sizeToScene();
        stage.toFront();
                
        Undecorator undecorator = undecoratorScene.getUndecorator();
        stage.setMinWidth(undecorator.getMinWidth());
        stage.setMinHeight(undecorator.getMinHeight());
        
        stage.show();
    }
}
