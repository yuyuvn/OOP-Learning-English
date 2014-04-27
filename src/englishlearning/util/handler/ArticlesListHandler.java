/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util.handler;

import englishlearning.model.Article;
import englishlearning.model.Articles;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Clicia
 */
public class ArticlesListHandler extends DefaultHandler {    
    // TODO: fetch data from url, pasrt to Articles
    // http://www.journaldev.com/1198/java-sax-parser-example-tutorial-to-parse-xml-to-list-of-objects
    // key của Articles là guid của Article
    
    private Articles articles;
    private Article article;
    
    public Articles getArticles() {
        return articles;
    }
}
