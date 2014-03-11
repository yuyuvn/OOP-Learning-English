/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import javafx.scene.control.Button;
import org.controlsfx.control.HyperlinkLabel;
import org.controlsfx.control.PopOver;

/**
 *
 * @author Clicia
 */
public interface ILoginView {    
    public HyperlinkLabel getLabel();
    public Button getButton();
    public PopOver getPopOver();
    public void setPopOver(PopOver popOver);
}
