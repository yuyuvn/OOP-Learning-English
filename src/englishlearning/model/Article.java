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
public class Article implements Serializable {
    private String guid;
    private String title;
    private String description;
    private String link;
    private Tags tags;
    private String imageUrl;
    private String content;
    
    //<editor-fold defaultstate="collapsed" desc="Must use builder">
    Article() {
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getter & setter">
    public String getGuid() {
        return guid;
    }
    
    public void setGuid(String guid) {
        this.guid = guid;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public Tags getTags() {
        return tags;
    }
    
    public void setTags(Tags tags) {
        this.tags = tags;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
//</editor-fold>
    
    
    @Override
    public String toString() {
        return "Article{" + "guid=" + guid + ", title=" + title + ", description=" + description + ", link=" + link + ", tags=" + tags + ", imageUrl=" + imageUrl + ", content=" + content + '}';
    }
}
