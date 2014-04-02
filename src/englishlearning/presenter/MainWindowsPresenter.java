/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.views.IMainWindowsView;
import englishlearning.viewscontroller.LoginView;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Clicia
 * @param <V>
 * @param <M>
 */
public class MainWindowsPresenter<V extends IMainWindowsView, M> extends Presenter<V,M> {
    private Stage stage;
    private LoginPresenter loginPresenter;
    
    // Drag Windows vars
    private Double initialX;
    private Double initialY;
    
    public void showWindows(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene((Parent) this.getView());
        
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }
    
    @Override
    public void setView(V view) {
        super.setView(view);
        
        init();
    }
    
    private void init() {
        getView().getExitButton().setOnAction((ActionEvent event) -> {
            stage.close();
        });
        
        
        loginPresenter = new LoginPresenter();
        LoginView loginView = new LoginView<>(loginPresenter);
        getView().setContains(loginView);
        
        // Drag Windows
        getView().getRootPane().setOnMousePressed((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                initialX = me.getSceneX();
                initialY = me.getSceneY();
            }
        });
        getView().getRootPane().setOnMouseDragged((MouseEvent me) -> {
           if (me.getButton() == MouseButton.PRIMARY) {
                stage.getScene().getWindow().setX(me.getScreenX() - initialX);
                stage.getScene().getWindow().setY(me.getScreenY() - initialY);
            } 
        });
    }
}
