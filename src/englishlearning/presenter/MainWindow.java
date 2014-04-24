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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private Presenter titleBar;
    
    // <editor-fold desc="Property content" defaultstate="collapsed">
    private final ObjectProperty<Presenter> content = new SimpleObjectProperty<>(this, "content");;
    public final Presenter getContent() { return content.get(); }
    public final void setContent(Presenter value) { content.set(value); }
    public final ObjectProperty<Presenter> contentProperty() { return content; }
    // </editor-fold>
    
    public MainWindow() {
        loadFXML();
        Init();
        
        Login login = new Login();
        login.setOnLogin((ActionEvent event) -> {
            // TODO
        });
        setContent(login);
    }
    
    public void showWindows(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene((Parent) this);
        
        stage.setScene(scene);
        stage.show();
        
        WindowsBehavior.setDragDrop(rootPane, stage);
    }
    
    private void Init() {        
        contentProperty().addListener((ObservableValue<? extends Node> observable, Node oldValue, Node newValue) -> {
            contains.getChildren().clear();
            contains.getChildren().add(newValue);
        });
    }
}
