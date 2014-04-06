/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.views.ITitleView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 * @param <V>
 * @param <M>
 */
public class TitlePresenter<V extends ITitleView,M> extends Presenter<V,M> {

    @Override
    protected void initialize() {
        addControlButtonHandle();
    }
    
    private void addControlButtonHandle() {
        getView().getMinimizeButton().setOnAction((ActionEvent event) -> {
            ((Stage)getView().getRoot().getScene().getWindow()).setIconified(true);
        });
        getView().getMaximizeButton().setOnAction((ActionEvent event) -> {
            Stage s = (Stage)getView().getRoot().getScene().getWindow();
            Button b = (Button)event.getSource();
            if (s.isMaximized()) {
                s.setMaximized(false);
                b.setText("Maximize");
            } else {
                s.setMaximized(true);
                b.setText("Restore");
            }
            
        });
        getView().getCloseButton().setOnAction((ActionEvent event) -> {
            ((Stage)getView().getRoot().getScene().getWindow()).close();
        });
    }
}
