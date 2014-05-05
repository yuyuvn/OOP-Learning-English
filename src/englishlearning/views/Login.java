/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import java.util.Collection;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Clicia
 */
public class Login extends Controller {
    @FXML TextField usernameTextField;
        
    //<editor-fold defaultstate="collapsed" desc="Property isLoged">
    private ReadOnlyBooleanWrapper loged;

    private void setLoged(boolean value) {
        logedProperty();
        loged.set(value);
    }
    
    public final boolean isLoged() {
        return logedProperty().get();
    }

    public final ReadOnlyBooleanProperty logedProperty() {
        if (loged==null) loged = new ReadOnlyBooleanWrapper(this, "isLoged", false);
        return loged;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property username">
    public final String getUsername() { return usernameProperty().get(); }
    public final void setUsername(String value) { usernameProperty().set(value); }
    public final StringProperty usernameProperty() { return usernameTextField.textProperty(); }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property users">
    private SetProperty<String> users;

    public final ObservableSet<String> getUsers() {
        return usersProperty().get();
    }

    public final void setUsers(ObservableSet value) {
        usersProperty().set(value);
    }

    public final SetProperty<String> usersProperty() {
        if (users == null) users = new SimpleSetProperty<>(this, "users", FXCollections.observableSet());
        return users;
    }
    
//</editor-fold>
    
    @FXML private void onLogin(ActionEvent event) {
        if (!getUsername().equals("")) setLoged(true);
    }
    
    @FXML private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER && !getUsername().equals("")) setLoged(true);
    }
    
    public void setAutoCompletion(Collection<?> dataProvider) {
        TextFields.bindAutoCompletion(usernameTextField, dataProvider);
    }
}