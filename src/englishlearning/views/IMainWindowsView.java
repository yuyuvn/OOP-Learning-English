/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.viewscontroller.View;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 *
 * @author Clicia
 */
public interface IMainWindowsView {
    public Button getExitButton();
    public void setContains(View control);
    public Pane getRootPane();
    public void setStatus(int i, String text);
}
