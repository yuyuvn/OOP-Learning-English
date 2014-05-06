/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Dictionary;
import englishlearning.model.Word;
import englishlearning.model.WordBuilder;
import englishlearning.model.model.IDictionary;

/**
 *
 * @author Clicia
 */
public class DictionaryWrapper extends Wrapper implements IDictionary {

    public DictionaryWrapper(Object data) {
        super(data);
    }

    @Override
    public Dictionary<String,String> getDictionary() {
        return (Dictionary<String,String>)getRawData();
    }

    @Override
    public String get(String word) {
        return getDictionary().get(word);
    }

    @Override
    public void add(String word, String mean) {
        getDictionary().put(word, mean);
    }

    @Override
    public String get(Word word) {
        String mean = get(word.getWord());
        if (mean != null) word.setMean(mean);
        else mean = word.getMean();
        return mean;
    }

    @Override
    public void add(Word word) {
        add(word.getWord(),word.getMean());
    }

    @Override
    public Word getWord(String word) {
        String mean = get(word);
        return WordBuilder.create().word(word).mean(mean == null ? "" : mean).build();
    }
}
