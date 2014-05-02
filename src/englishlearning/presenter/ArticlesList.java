/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.util.CustomTextFields;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomTextField;

/**
 *
 * @author Clicia
 */
public class ArticlesList extends Controller {   
    @FXML private CustomTextField searchField;
    
    //<editor-fold defaultstate="collapsed" desc="Property isLoading">
    private BooleanProperty isLoading;
    
    public boolean isIsLoading() {
        return isLoadingProperty().get();
    }
    
    public void setIsLoading(boolean value) {
        isLoadingProperty().set(value);
    }
    
    public BooleanProperty isLoadingProperty() {
        if (isLoading == null) isLoading = new SimpleBooleanProperty(true);
        return isLoading;
    }
//</editor-fold> 
    
    public ArticlesList() {
    }
}
