/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clicia
 */
public class ArticleBuilder {
    private final Article data;

    protected ArticleBuilder() {
        this.data = new Article();
        this.data.tags = new ArrayList<>();
    }
    
    public static ArticleBuilder create() {
        return new ArticleBuilder();
    }
    
    public Article build() {
        if (data.guid == null) throw new RuntimeException("Guid is not set");
        if (data.link == null) data.link = data.guid;
        if (data.title == null) data.title = "";
        if (data.description == null) data.description = "";
        if (data.content == null) data.content = "";
        return data;
    }
    
    public ArticleBuilder guid(String guid) {
        data.guid = guid;
        return this;
    }
    
    public ArticleBuilder title(String title) {
        data.title = title;
        return this;
    }
    
    public ArticleBuilder description(String description) {
        data.description = description;
        return this;
    }
    
    public ArticleBuilder link(String link) {
        data.link = link;
        return this;
    }
    
    public ArticleBuilder tags(String tags) {
        data.tags.add(tags);
        return this;
    }
    
    public ArticleBuilder tags(List<String> tags) {
        data.tags.addAll(tags);
        return this;
    }
    
    public ArticleBuilder imageUrl(String imageUrl) {
        try {
            data.imageUrl = new URL(imageUrl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ArticleBuilder.class.getName()).log(Level.WARNING, null, ex);
        }
        return this;
    }
    
    public ArticleBuilder imageUrl(URL imageUrl) {
        data.imageUrl = imageUrl;
        return this;
    }
    
    public ArticleBuilder content(String content) {
        data.content = content;
        return this;
    }
}
