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
        this((T)WordBuilder.create().word("").mean("").build());
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
        Word word = getWord();
        int answer = word.getAnswer();
        return (answer > 0) && (word.getAnswer() == word.getChoiced());
    }

    @Override
    public boolean isAnswered() {
        return getWord().getAnswer() > 0;
    }
    // getWord / setWord  conflict
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
    public int getAnswer(){
        return getWord().getAnswer();
    }
    @Override
    public void setAnswer(int answer){
        if(answer > 0 && answer < 5){
            getWord().setAnswer(answer);
        } 
    }
    @Override
    public int getChoiced(){
        return getWord().getChoiced();
    }
    @Override
    public void setChoiced(int choiced){
        if(choiced > 0 && choiced < 5){
            getWord().setChoiced(choiced);
        }
    }
}
