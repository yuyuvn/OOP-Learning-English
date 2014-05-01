/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util.handler;

import englishlearning.model.Article;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Clicia
 */
public class ArticleHandler extends DefaultHandler {
    // TODO: get content and set for article
    // see ArticlesListHandler.java
    // http://www.journaldev.com/1198/java-sax-parser-example-tutorial-to-parse-xml-to-list-of-objects
    // http://learningenglish.voanews.com/content/words-and-their-stories-belittle/1578052.html
    
    private final Article article;
    
    public ArticleHandler(Article article) {
        super();
        this.article = article;
    }
}
