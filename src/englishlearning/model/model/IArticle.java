package englishlearning.model.model;

import englishlearning.model.Article;
import javafx.scene.image.Image;


/**
 *
 * @author PC
 */
public interface IArticle extends IWrapper {
//TODO
    Article getArticle();
    
    Image getThumbnail();
    String getParsedContent();
}
