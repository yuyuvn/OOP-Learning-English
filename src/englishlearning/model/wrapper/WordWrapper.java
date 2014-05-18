/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Options;
import englishlearning.model.Word;
import englishlearning.model.WordBuilder;
import englishlearning.model.model.IWord;

/**
 *
 * @author PC
 * @param <T>
 */
public class WordWrapper<T extends Word> extends Wrapper<T> implements IWord {

    public WordWrapper() {
        this((T)WordBuilder.create().text("").mean("").build());
    }
    
    public WordWrapper(T data) {
        super(data);
    }

    @Override
    public Word getWord() {
        return (Word)getRawData();
    }

    @Override
    public boolean isRightAnswer() {
        return getMean().equals(getChose());
    }	
	
    @Override
    public boolean isAnswered() {
        return getWord().getChose() != null;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    @Override
    public String getMean(){
        return getWord().getMean();
    }
    @Override
    public void setMean(String mean){
        getWord().setMean(mean);
    }
    @Override
    public Options getOptions(){
        return getWord().getOptions();
    }
    @Override
    public void setOptions(Options options){
        getWord().setOptions(options);
    }
    
    @Override
    public String getChose() {
        return getWord().getChose();
    }
    
    @Override
    public void SetChose(String chose) {
        getWord().setChose(chose);
    }
//</editor-fold>

    @Override
    public String getText() {
        return getWord().getText();
    }

    @Override
    public void setText(String text) {
        getWord().setText(text);
    }
}
