/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controller;

import englishlearning.model.UsersList;
import englishlearning.util.DataInDisk;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Clicia
 */
public class Login extends Controller {
    @FXML TextField usernameTextField;
    
    //<editor-fold defaultstate="collapsed" desc="Property isLoged">
    private BooleanProperty isLoged;
    public final Boolean getIsLoged() { return isLogedProperty().get(); }
    public final void setIsLoged(Boolean value) { isLogedProperty().set(value); }
    public final BooleanProperty isLogedProperty() { 
        if (isLoged == null) {
            isLoged = new SimpleBooleanProperty(this, "isLoged", false);
        }
        return isLoged; 
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property username">
    private StringProperty username;
    public final String getUsername() { return usernameProperty().get(); }
    public final void setUsername(String value) { usernameProperty().set(value); }
    public final StringProperty usernameProperty() {
        if (username == null) {
            username = new SimpleStringProperty(this, "username", "");
        }
        return username; 
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property users">
    private final ObjectProperty<UsersList> users = new SimpleObjectProperty<>(this, "users");
    public final UsersList getUsers() { return users.get(); }
    public final void setUsers(UsersList value) { users.set(value); }
    public final ObjectProperty<UsersList> usersProperty() { return users; }
//</editor-fold>
        
    public Login() {        
        Bindings.bindBidirectional(
                usernameProperty(),
                usernameTextField.textProperty());
        
        setUsers(DataInDisk.getUsersList());
        TextFields.bindAutoCompletion(usernameTextField, getUsers());
        
        isLogedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            getUsers().add(getUsername());
            DataInDisk.saveUsersList(getUsers());
        });
    }
    
    public final void onLogin(ActionEvent event) {
        if (!getUsername().equals("")) setIsLoged(true);
    }
}