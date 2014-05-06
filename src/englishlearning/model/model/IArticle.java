package englishlearning.model.model;

import englishlearning.model.Article;
import javafx.scene.image.Image;


/**
 *
 * @author PC
 */
public interface IArticle extends IWrapper {
    Article getArticle();
    
    Image getThumbnail();
    String getParsedContent();
}
