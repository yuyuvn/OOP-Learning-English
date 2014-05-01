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
public class QuestionBuilder {
    // TODO
    private final Question data;
    
    private QuestionBuilder() {
        data = new Question();
    }
    
    public static QuestionBuilder create() {
        return new QuestionBuilder();
    }
    
    public QuestionBuilder word(String word) {
        data.setWord(word);
        return this;
    }
    
    public Question build() {
        if (data.getWord() == null) throw new RuntimeException("Word is not set");
        return data;
    }
}
