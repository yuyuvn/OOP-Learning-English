/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.UserInfo;
import englishlearning.model.wrapper.UserWrapper;
import englishlearning.model.wrapper.WrapperProperty;
import englishlearning.presenter.Controller.FXMLPath;
import englishlearning.util.DataInDisk;
import javafx.beans.binding.Bindings;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 */
@FXMLPath("Window.fxml")
public class MainWindow extends Window {
    //<editor-fold defaultstate="collapsed" desc="Property User">
    private WrapperProperty<UserWrapper<UserInfo>> user;
    public final UserWrapper<UserInfo> getUser() { return userProperty().get(); }
    protected final void setUser(UserWrapper<UserInfo> value) { userProperty().set(value); }
    public final WrapperProperty<UserWrapper<UserInfo>> userProperty() { 
        if (user == null) user = new WrapperProperty(this, "user");
        return user; 
    }
//</editor-fold>
    
    public MainWindow(Stage stage, String username) {
        super(stage);
        
        setMinWidth(900);
        setMinHeight(600);
        
        UserWrapper<UserInfo> userwrapper = new UserWrapper(DataInDisk.getUserInfo(username));
        setUser(userwrapper);
        
        if (userwrapper.getRawData().playState == null) {
            MainContent mainContent = new MainContent();
            Bindings.bindBidirectional(
                    mainContent.userProperty(),
                    userProperty());
            this.setContent(mainContent);
        } else {
        //TODO: resume play state
        }
        
        this.setTitle("VOA Learning English");
        this.showWindow();
    }
    
}
