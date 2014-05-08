/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Clicia
 */
public class WordBuilder {
    // TODO
    private final Word data;
    
    protected WordBuilder() {
        data = new Word();
        data.setAnswer(0);
        data.setChoiced(0);
        data.setOptions(new Options());
    }
    
    public static WordBuilder create() {
        return new WordBuilder();
    }
    
    public WordBuilder word(String value) {
        data.setWord(value);
        return this;
    }
    
    public WordBuilder mean(String value) {
        data.setMean(value);
        return this;
    }
    
    public WordBuilder options(String value) {
        data.getOptions().add(value);
        return this;
    }
    
    public WordBuilder options(Options value) {
        data.setOptions(value);
        return this;
    }
    
    public Word build() {
        if (data.getWord() == null) throw new RuntimeException("Word is not set");
        return data;
    }
}
