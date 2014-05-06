/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

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
        this((T)WordBuilder.create().word("").build());
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
}
