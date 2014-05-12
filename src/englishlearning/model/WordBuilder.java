/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

/**
 *
 * @author Clicia
 */
public class WordBuilder {
    // TODO
    private final Word data;
    
    protected WordBuilder() {
        data = new Word();
        data.setChose(null);
        data.setOptions(new Options());
    }
    
    public static WordBuilder create() {
        return new WordBuilder();
    }
    
    public WordBuilder text(String value) {
        data.setText(value);
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
        if (data.getText() == null) throw new RuntimeException("Text is not set");
        return data;
    }
}
