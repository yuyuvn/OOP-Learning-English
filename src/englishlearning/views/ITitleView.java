/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author Clicia
 */
public interface ITitleView {
    public Button getCloseButton();
    public Button getMaximizeButton();
    public Button getMinimizeButton();
    public Pane getRoot();
}
