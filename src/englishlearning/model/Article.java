/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Clicia
 */
public class Article implements Serializable {
    public String guid;
    public String title;
    public String description;
    public URL link;
    public List<String> tags;
    public URL imageUrl;
    public String content;
    
    //<editor-fold defaultstate="collapsed" desc="Must use builder">
    Article() {
    }

//</editor-fold>
    
    @Override
    public String toString() {
        return "Article{" + "guid=" + guid + ", title=" + title + ", description=" + description + ", link=" + link + ", tags=" + tags + ", imageUrl=" + imageUrl + ", content=" + content + '}';
    }
}
