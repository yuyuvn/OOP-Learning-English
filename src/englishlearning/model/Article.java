/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Clicia
 */
public class Article implements Serializable {
    private String guid;
    private String title;    
    private String description;
    private URL link;
    private Date pubDate; // http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
    private Set<String> tags;
    private URL imageUrl;
    private String content;
}
