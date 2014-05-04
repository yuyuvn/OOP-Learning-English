/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Article;
import englishlearning.model.ArticleBuilder;
import englishlearning.model.model.IArticle;
import javafx.scene.image.Image;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class ArticleWrapper<T extends Article> extends Wrapper<T> implements IArticle {

    public ArticleWrapper(T data) {
        super(data);
    }
    
    public ArticleWrapper() {
        super((T)ArticleBuilder.create().guid("").build());
    }
    
    @Override
    public Article getArticle() {
        return getRawData();
    }

    private Image __thumb = null;
    @Override
    public Image getThumbnail() {
        if (__thumb == null) {
            __thumb = new Image(getRawData().getImageUrl(), 0.0, 0.0, false, false, true);
        }
        
        return __thumb;
    }
}
