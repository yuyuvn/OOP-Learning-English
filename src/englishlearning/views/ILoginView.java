/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Clicia
 */
public interface ILoginView extends Initializable {
    @FXML
    public void handleButtonAction(ActionEvent event);
    public void setLabel(String text);
}
