/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.Article;
import englishlearning.model.Articles;
import java.net.URL;

/**
 *
 * @author Clicia
 */
public class DataInNet {
    private final static String URL_RSS = "http://learningenglish.voanews.com/api/epiqq";
    
    public static Articles getListArticle(String url) {
        // TODO: fetch data from url, pasrt to Articles
        // http://www.journaldev.com/1198/java-sax-parser-example-tutorial-to-parse-xml-to-list-of-objects
        
        Articles<String,Article> articles = new Articles<>();
        
        return articles;
    }
    
    public static Articles getListArticle() {
        return getListArticle(URL_RSS);
    }
}
