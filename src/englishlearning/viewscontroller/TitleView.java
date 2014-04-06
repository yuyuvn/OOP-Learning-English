/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.viewscontroller;

import englishlearning.views.ITitleView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author Clicia
 */
public class TitleView extends View implements ITitleView {
    @FXML protected Button close;
    @FXML protected Button maximize;
    @FXML protected Button minimize;

    @Override
    public Button getCloseButton() {
        return close;
    }

    @Override
    public Button getMaximizeButton() {
        return maximize;
    }

    @Override
    public Button getMinimizeButton() {
        return minimize;
    }
    
    @Override
    public Pane getRoot() {
        return this;
    }
}
