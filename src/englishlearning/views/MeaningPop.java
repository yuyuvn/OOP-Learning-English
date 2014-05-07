/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IWord;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

/**
 *
 * @author Clicia
 */
public class MeaningPop extends Controller implements DataReceivable {
    
    //<editor-fold defaultstate="collapsed" desc="String mean">
    private ReadOnlyStringWrapper mean;
    
    public String getMean() {
        return _meanProperty().get();
    }
    
    private void setMean(String value) {
        _meanProperty().set(value);
    }
    
    public ReadOnlyStringProperty meanProperty() {
        return _meanProperty().getReadOnlyProperty();
    }
    
    public ReadOnlyStringWrapper _meanProperty() {
        if (mean == null) mean  = new ReadOnlyStringWrapper(this, "mean");
        return mean;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="String word">
    private ReadOnlyStringWrapper word;
    
    public String getWord() {
        return _wordProperty().get();
    }
    
    private void setWord(String value) {
        _wordProperty().set(value);
    }
    
    public ReadOnlyStringProperty wordProperty() {
        return _wordProperty().getReadOnlyProperty();
    }
    
    public ReadOnlyStringWrapper _wordProperty() {
        if (word == null) word  = new ReadOnlyStringWrapper(this, "word");
        return word;
    }
//</editor-fold>
    

    @Override
    public void setData(Object value) {
        try {
            IWord w = (IWord)value;
            setMean(w.getWord().getMean());
            setWord(w.getWord().getWord());
        } catch (ClassCastException e) {
        }
    }
    
}
