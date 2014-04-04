/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.UsersList;
import englishlearning.util.WindowsBehavior;
import englishlearning.views.IMainWindowsView;
import englishlearning.viewscontroller.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    
    public void showWindows(Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene((Parent) this.getView());
        
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
        
        WindowsBehavior.setDragDrop(getView().getRootPane(), stage);
    }

    @Override
    protected void initialize() {
        addControlButtonHandle();
        
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginView loginView = new LoginView<>(loginPresenter);
        getView().setContains(loginView);
        
        loginPresenter.setOnLogin(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO: tao presenter cho main contains
            }
        });
    }
    
    private void addControlButtonHandle() {
        getView().getExitButton().setOnAction((ActionEvent event) -> {
            stage.close();
        });
    }
}
