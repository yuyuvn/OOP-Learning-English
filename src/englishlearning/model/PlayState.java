/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Clicia
 */
public class PlayState implements Serializable {
    private final String articleGUID;
    private transient Article article;
    
    private Map<String,Object> questions; // TODO change object to question model

    public PlayState(String articleGUID) {
        this.articleGUID = articleGUID;
    }

    public String getArticleGUID() {
        return articleGUID;
    }

    public Map<String, Object> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Object> allQuestions) {
        this.questions = allQuestions;
    }
    
    public Article getArticle() {
        if (article == null) {
            // TODO get Article from articleGUID
            article = new Article();
        }
        return article;
    }
    
    public int getScore() {
        // TODO count true answer, use stream & lambda expression
        int s = 0;
        
        return s;
    }
}
