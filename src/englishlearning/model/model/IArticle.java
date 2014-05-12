package englishlearning.model.model;

import englishlearning.model.Article;
import englishlearning.model.Tags;
import javafx.scene.image.Image;


/**
 *
 * @author PC
 */
public interface IArticle extends IWrapper {
    Article getArticle();
    
    Image getThumbnail();    
    Image getThumbnail(boolean cache);
    
    String getGuid();
    void setGuid(String guid);
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    String getLink();
    void setLink(String link);
    Tags getTags();
    void setTags(Tags tags);
    String getImageUrl();
    void setImageUrl(String imageUrl);
    String getContent();
    void setContent(String content);
}
