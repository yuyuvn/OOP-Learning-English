/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Article;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class ArticleWrapper<T extends Article> extends Wrapper<T> {

    public ArticleWrapper(T data) {
        super(data);
    }
    
    
}
