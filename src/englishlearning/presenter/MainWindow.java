/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.util.WindowsBehavior;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
    @FXML
    private Pane rootPane;
    @FXML
    private Presenter captionBar;
    
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
                this.setPrefSize(960, 640);
                
                setContent(mainContent);
            }
        });
        setContent(login);
    }
    
    public void showWindows(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene((Parent) this);
        
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
        
        WindowsBehavior.setDragDrop(rootPane, stage);
    }
}
