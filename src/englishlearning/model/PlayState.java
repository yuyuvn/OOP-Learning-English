/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Clicia
 */
public class PlayState implements Serializable {
    private final String articleGUID;    
    private List<Word> questions;

    public PlayState(String articleGUID) {
        this.articleGUID = articleGUID;
    }

    public String getArticleGUID() {
        return articleGUID;
    }

    public List<Word> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Word> allQuestions) {
        this.questions = allQuestions;
    }
    
    /*public Article getArticle() {
    if (article == null) {
    // TODO get Article from articleGUID
    article = new Article();
    }
    return article;
    }
    
    public long getScore() {
    // TODO change filter condition
    return questions.stream().filter(q -> q.equals(q)).count();
    }*/
}
