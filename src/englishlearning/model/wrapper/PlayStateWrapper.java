/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Article;
import englishlearning.model.PlayState;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class PlayStateWrapper<T extends PlayState> extends Wrapper<T> {
    private Article article;
    
    public PlayStateWrapper(T data) {
        super(data);
    }
    
    public int getScore() {
        // TODO count true answer
        int s = 0;
        
        return s;
    }
    
    public void addWord(String word) {
        // TODO create other mean
        if (getRawData().allQuestions.put(word, new Object()) == null)
            getRawData().questions.add(word);
    }
    
    public Article getArticle() {
        if (article == null) {
            // get Article from getRawData().articleGUID
            article = new Article();
        }
        return article;
    }
}
