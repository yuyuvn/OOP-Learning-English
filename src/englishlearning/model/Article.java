/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Clicia
 */
public class Article implements Serializable {
    public String guid;
    public String title;
    public String description;
    public URL link;
    public Date pubDate; // http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
    public HashSet<String> tags;
    public URL imageUrl;
    public String content;
}
