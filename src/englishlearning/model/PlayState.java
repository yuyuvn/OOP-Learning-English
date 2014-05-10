/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.Collection;


/**
 *
 * @author Clicia
 */
public class PlayState extends java.util.ArrayList<Word> {
    private String article;
    
    public PlayState(String guid) {
        super();
        setArticle(guid);
    }
    
    public final String getArticle() {
        return article;
    }

    public final void setArticle(String article) {
        this.article = article;
    }
    
    public int getPoint() {
        return (int)this.stream().filter(p -> p.getAnswer() == p.getChoiced() && p.getChoiced() > 0).count();
    }
}
