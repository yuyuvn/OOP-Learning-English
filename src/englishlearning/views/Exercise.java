/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IWord;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
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
}
