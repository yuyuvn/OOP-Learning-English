/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.UsersList;
import englishlearning.model.wrapper.UserWrapper;
import englishlearning.util.DataInDisk;
import englishlearning.views.LoginWindow;
import englishlearning.views.MainWindow;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 * @param <V>
 */
public class LoginPresenter<V extends LoginWindow> extends Presenter<V> {

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
    
    public LoginPresenter(V view) {
        super(view);
    }

    public LoginPresenter() {
        super();
    }
        
    @Override
    protected void initialize() {
        UsersList usersList = DataInDisk.getUsersList();
        setUsers(FXCollections.observableSet(usersList));
        getView().getLogin().setAutoCompletion(usersList);
        
        // resume data before add change handler
        usersProperty().addListener((ObservableValue<? extends ObservableSet<String>> observable, ObservableSet<String> oldValue, ObservableSet<String> newValue)->{
            UsersList ul = new UsersList(newValue);
            getView().getLogin().setAutoCompletion(ul);
            DataInDisk.saveUsersList(ul);
        });
        
        getView().getLogin().logedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                String username = getView().getLogin().getUsername();
                getUsers().add(username);
                System.out.println("User " + username + " logged");

                getView().closeWindow();
                
                MainPresenter mainPresenter = new MainPresenter();
                mainPresenter.setUser(new UserWrapper(DataInDisk.getUserInfo(username)));
                Stage stage = new Stage();
                MainWindow mainWindow = new MainWindow(stage,mainPresenter);
            }
        });
    }
}
