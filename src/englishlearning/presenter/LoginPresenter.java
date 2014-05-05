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
import java.util.Collection;
import java.util.Set;
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

    public LoginPresenter(V view) {
        super(view);
    }

    public LoginPresenter() {
        super();
    }
        
    @Override
    protected void initialize() {
        UsersList<String> users = DataInDisk.getUsersList();
        getView().getLogin().setUsers(FXCollections.observableSet(users));
        getView().getLogin().setAutoCompletion(users);
        
        // resume data before ad change handler
        getView().getLogin().usersProperty().addListener((ObservableValue<? extends ObservableSet<String>> observable, ObservableSet<String> oldValue, ObservableSet<String> newValue)->{
            UsersList<String> usersList = new UsersList<>(newValue);
            getView().getLogin().setAutoCompletion(usersList);
            DataInDisk.saveUsersList(usersList);
        });
        
        getView().getLogin().logedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                String username = getView().getLogin().getUsername();
                getView().getLogin().getUsers().add(username);
                System.out.println("User " + username + " logged");

                //MainWindow mainWindow = new MainWindow(new Stage(), login.getUsername());
                getView().closeWindow();
                
                MainPresenter mainPresenter = new MainPresenter();
                mainPresenter.setUser(new UserWrapper(DataInDisk.getUserInfo(username)));
                Stage stage = new Stage();
                MainWindow mainWindow = new MainWindow(stage,mainPresenter);
            }
        });
    }
}
