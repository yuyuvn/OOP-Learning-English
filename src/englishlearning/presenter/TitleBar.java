/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 */
public class TitleBar extends Presenter {
    //<editor-fold defaultstate="collapsed" desc="Property minimize">
    private final StringProperty minimize = new SimpleStringProperty(this, "minimize", "Minimize");;
    public final String getMinimize() { return minimize.get(); }
    public final void setMinimize(String value) { minimize.set(value); }
    public final StringProperty minimizeProperty() { return minimize; }
//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Property maximize">
    private final StringProperty maximize = new SimpleStringProperty(this, "maximize", "Maximize");;
    public final String getMaximize() { return maximize.get(); }
    public final void setMaximize(String value) { maximize.set(value); }
    public final StringProperty maximizeProperty() { return maximize; }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property close">
    private final StringProperty close = new SimpleStringProperty(this, "close", "Close");;
    public final String getClose() { return close.get(); }
    public final void setClose(String value) { close.set(value); }
    public final StringProperty closeProperty() { return close; }
//</editor-fold>
    
    public TitleBar() {
        loadFXML();
    }

    @FXML
    private void onMinimize(ActionEvent event) {
        ((Stage)getScene().getWindow()).setIconified(true);
    }
    
    @FXML
    private void onMaximize(ActionEvent event) {
        Stage s = (Stage)getScene().getWindow();
        if (s.isMaximized()) {
            s.setMaximized(false);
            setMaximize("Maximize");
        } else {
            s.setMaximized(true);
            setMaximize("Restore");
        }
    }
    
    @FXML
    private void onClose(ActionEvent event) {
        ((Stage)getScene().getWindow()).close();
    }
}
