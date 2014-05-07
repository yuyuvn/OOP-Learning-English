/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IWord;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleDoubleProperty;
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
    //<editor-fold defaultstate="collapsed" desc="String word">
    private StringProperty word;
    
    private String getWord() {
        return stringProperty().get();
    }
    
    private void setWord(String value) {
        stringProperty().set(value);
    }
    
    private StringProperty stringProperty() {
        if (word == null) word = new SimpleStringProperty(this, "word", "");
        return word;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="String option1">
    private StringProperty option1;
    
    private String getOption1() {
        return stringProperty().get();
    }
    
    private void setOption1(String value) {
        stringProperty().set(value);
    }
    
    private StringProperty option1Property() {
        if (option1 == null) option1 = new SimpleStringProperty(this, "option1", "");
        return option1;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="String option2">
    private StringProperty option2;
    
    private String getOption2() {
        return stringProperty().get();
    }
    
    private void setOption2(String value) {
        stringProperty().set(value);
    }
    
    private StringProperty option2Property() {
        if (option2 == null) option2 = new SimpleStringProperty(this, "option2", "");
        return option2;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="String option3">
    private StringProperty option3;
    
    private String getOption3() {
        return stringProperty().get();
    }
    
    private void setOption3(String value) {
        stringProperty().set(value);
    }
    
    private StringProperty option3Property() {
        if (option3 == null) option3 = new SimpleStringProperty(this, "option3", "");
        return option3;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="String option4">
    private StringProperty option4;
    
    private String getOption4() {
        return stringProperty().get();
    }
    
    private void setOption4(String value) {
        stringProperty().set(value);
    }
    
    private StringProperty option4Property() {
        if (option4 == null) option4 = new SimpleStringProperty(this, "option4", "");
        return option4;
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
            Object[] options = w.getWord().getOptions().toArray();
            setOption1(options[0].toString());
            setOption2(options[1].toString());
            setOption3(options[2].toString());
            setOption4(options[3].toString());
            setChoice(0);
        } catch (ClassCastException e) {
        }
    }
    
    @FXML private void onSelect(ActionEvent event) {
        Button source = (Button)event.getSource();
        String selected = source.getText();
        if (selected.equals(getOption1())) {
            setChoice(1);
        } else if (selected.equals(getOption2())) {
            setChoice(2);
        } else if (selected.equals(getOption3())) {
            setChoice(3);
        } else if (selected.equals(getOption4())) {
            setChoice(4);
        }
    }
}
