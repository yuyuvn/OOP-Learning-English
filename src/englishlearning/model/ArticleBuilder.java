/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Clicia
 */
public class ArticleBuilder {
    private final Article data;

    protected ArticleBuilder() {
        this.data = new Article();
        this.data.setTags(new ArrayList<>());
    }
    
    public static ArticleBuilder create() {
        return new ArticleBuilder();
    }
    
    public Article build() {
        if (data.getGuid() == null) throw new RuntimeException("Guid is not set");
        if (data.getLink() == null) data.setLink(data.getGuid());
        if (data.getTitle() == null) data.setTitle("");
        if (data.getDescription() == null) data.setDescription("");
        if (data.getContent() == null) data.setContent("");
        return data;
    }
    
    public ArticleBuilder guid(String guid) {
        data.setGuid(guid);
        return this;
    }
    
    public ArticleBuilder title(String title) {
        data.setTitle(title);
        return this;
    }
    
    public ArticleBuilder description(String description) {
        data.setDescription(description);
        return this;
    }
    
    public ArticleBuilder link(String link) {
        data.setLink(link);
        return this;
    }
    
    public ArticleBuilder tags(String tags) {
        data.getTags().add(tags);
        return this;
    }
    
    public ArticleBuilder tags(List<String> tags) {
        data.getTags().addAll(tags);
        return this;
    }
    
    public ArticleBuilder imageUrl(String imageUrl) {
        data.setImageUrl(imageUrl);
        return this;
    }
    
    public ArticleBuilder content(String content) {
        data.setContent(content);
        return this;
    }
}
