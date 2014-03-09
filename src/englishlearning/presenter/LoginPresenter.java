/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.views.ILoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 * @param <V>
 * @param <M>
 */
public class LoginPresenter<V extends ILoginView, M> extends Presenter<V,M> {
    private Stage stage;
    
    public void showWindows(Stage stage) {
        Scene scene = new Scene((Parent) this.getView());
        
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }
    
    @Override
    public void setView(V view) {
        super.setView(view);
        
        this.getView().getButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                getView().getLabel().setText("Hello World!");
            }
            
        });
    }
}
