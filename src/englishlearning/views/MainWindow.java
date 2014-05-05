/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IUser;
import englishlearning.model.wrapper.UserWrapper;
import englishlearning.model.property.WrapperProperty;
import englishlearning.presenter.Presenter;
import englishlearning.views.Controller.FXMLPath;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 */
@FXMLPath("Window.fxml")
public class MainWindow extends Window {
    //<editor-fold defaultstate="collapsed" desc="Property User">
    private WrapperProperty<IUser> user;
    public final IUser getUser() { return userProperty().get(); }
    public final void setUser(IUser value) { userProperty().set(value); }
    public final WrapperProperty<IUser> userProperty() { 
        if (user == null) user = new WrapperProperty(new UserWrapper());
        return user; 
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property mainContent">
    private ReadOnlyObjectWrapper<MainContent> mainContent;
    
    public final MainContent getMainContent() {
        return mainContentProperty().get();
    }
    
    public final ReadOnlyObjectWrapper<MainContent> mainContentProperty() {
        if (mainContent == null) {
            MainContent mc = new MainContent();
            Bindings.bindBidirectional(
                    mc.userProperty(),
                    userProperty());
            mainContent = new ReadOnlyObjectWrapper<>(this, "mainContent", mc);
        }
        return mainContent;
    }
//</editor-fold>
    
    public MainWindow(Stage stage, Presenter presenter) {
        super(stage, presenter);
        
        setMinWidth(900);
        setMinHeight(600);        
        
        this.setTitle("VOA Learning English");
        this.setContent(getMainContent());
        this.showWindow();
    }
    
}
