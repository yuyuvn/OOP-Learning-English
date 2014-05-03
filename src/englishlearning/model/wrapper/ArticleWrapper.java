/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Article;
import englishlearning.model.model.IArticle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
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
    
    @Override
    public Article getArticle() {
        return getRawData();
    }

    private Image __thumb = null;
    @Override
    public Image getThumbnail() {
        if (__thumb == null) {
            __thumb = new Image(getRawData().getImageUrl(), 200.0, 100.0, true, true, true);
        }
        
        return __thumb;
    }
}
