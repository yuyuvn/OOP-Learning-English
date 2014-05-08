package englishlearning.model.model;

import englishlearning.model.Article;
import java.util.List;
import javafx.scene.image.Image;


/**
 *
 * @author PC
 */
public interface IArticle extends IWrapper {
    Article getArticle();
    
    Image getThumbnail();
    String getParsedContent();
    public String getGuid();
    public void setGuid(String guid);
    public String getTitle();
    public void setTitle(String title);
    public String getDescription();
    public void setDescription(String description);
    public String getLink();
    public void setLink(String link);
    public List getTags();
    public void setTags(List tags);
    public String getImageUrl();
    public void setImageUrl(String imageUrl);
    public String getContent();
    public void setContent(String content);
}
