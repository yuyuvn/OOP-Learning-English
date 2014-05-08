/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IWord;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.WordWrapper;

/**
 *
 * @author Clicia
 */
public class ResultItem extends Controller implements DataReceivable {
    //<editor-fold defaultstate="collapsed" desc="IWord data">
    private WrapperProperty<IWord> data;
    
    public IWord getData() {
        return dataProperty().get();
    }
    
    public void setData(IWord value) {
        dataProperty().set(value);
        if (value.isAnswered()) this.getStyleClass().add("result-tem-true");
        else this.getStyleClass().add("result-item-false");
    }
    
    public WrapperProperty<IWord> dataProperty() {
        if (data == null) data = new WrapperProperty<>(this,"data", new WordWrapper());
        return data;
    }
//</editor-fold>    
    
    @Override
    public void setData(Object value) {
        try {
            setData((IWord)value);
        } catch (ClassCastException e) {
        }
    }
    
}
