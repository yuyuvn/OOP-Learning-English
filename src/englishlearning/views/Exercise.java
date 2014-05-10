/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IUser;
import englishlearning.model.model.IWord;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.UserWrapper;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Clicia
 */
public class Exercise extends Controller implements DataReceivable {
    @FXML private Button option1;
    @FXML private Button option2;
    @FXML private Button option3;
    @FXML private Button option4;
    
    
    //<editor-fold defaultstate="collapsed" desc="IUser User">
    private WrapperProperty<IUser> user;
    public final IUser getUser() { return userProperty().get(); }
    public final void setUser(IUser value) { userProperty().set(value); }
    public final WrapperProperty<IUser> userProperty() { 
        if (user == null) user = new WrapperProperty(this, "user", new UserWrapper());
        return user; 
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="String word">
    private StringProperty word;
    
    public String getWord() {
        return wordProperty().get();
    }
    
    public void setWord(String value) {
        wordProperty().set(value);
    }
    
    public StringProperty wordProperty() {
        if (word == null) word = new SimpleStringProperty(this, "word", "");
        return word;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="int choice">
    private ReadOnlyIntegerWrapper choice;
    
    public int getChoice() {
        return _choiceProperty().get();
    }
    
    private void setChoice(int value) {
        _choiceProperty().set(value);
    }
    
    public ReadOnlyIntegerProperty choiceProperty() {
        return _choiceProperty().getReadOnlyProperty();
    }
    
    public ReadOnlyIntegerWrapper _choiceProperty() {
        if (choice == null) choice = new ReadOnlyIntegerWrapper(this, "choice", 0);
        return choice;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Double process">
    private DoubleProperty process;
    
    public double getProcess() {
        return processProperty().get();
    }
    
    public void setProcess(double value) {
        processProperty().set(value);
    }
    
    public DoubleProperty processProperty() {
        if (process == null) process = new SimpleDoubleProperty(this, "process", 0);
        return process;
    }
//</editor-fold>
    
    @Override
    public void setData(Object value) {
        try {
            IWord w = (IWord)value;
            setWord(w.getWord().getWord());
            List<String> options = new ArrayList();
            options.addAll(w.getWord().getOptions());
            option1.setText(options.get(0));
            option2.setText(options.get(1));
            option3.setText(options.get(2));
            option4.setText(options.get(3));
            setChoice(0);
        } catch (ClassCastException e) {
        }
    }
    
    @FXML private void onSelect(ActionEvent event) {
        Object source = event.getSource();
        
        if (source == option1) {
            setChoice(1);
        } else if (source == option2) {
            setChoice(2);
        } else if (source == option3) {
            setChoice(3);
        } else if (source == option4) {
            setChoice(4);
        }
    }
    
    private EventHandler onReturn;
    public void setOnReturn(EventHandler eventHandler) {
        onReturn = eventHandler;
    }    
    public void onReturn(ActionEvent event) {
        if (onReturn != null) onReturn.handle(event);
    }
}
