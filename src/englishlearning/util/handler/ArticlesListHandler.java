/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util.handler;

import englishlearning.model.ArticleBuilder;
import englishlearning.model.Articles;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Clicia
 */
public class ArticlesListHandler extends DefaultHandler {
    // http://www.journaldev.com/1198/java-sax-parser-example-tutorial-to-parse-xml-to-list-of-objects
    // http://learningenglish.voanews.com/api/epiqq
    
    private final Articles articles = new Articles();
    private ArticleBuilder articleBuilder;
    
    private boolean __title = false;
    private boolean __description = false;
    private boolean __link = false;
    private boolean __guid = false;
    private boolean __category = false;
    
    public Articles getArticles() {
        return articles;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            articleBuilder = ArticleBuilder.create();
        } else if (qName.equalsIgnoreCase("title")) {
            __title = true;
        } else if (qName.equalsIgnoreCase("description")) {
            __description = true;
        } else if (qName.equalsIgnoreCase("link")) {
            __link = true;
        } else if (qName.equalsIgnoreCase("guid")) {
            __guid = true;
        } else if (qName.equalsIgnoreCase("category")) {
            __category = true;
        } else if (qName.equalsIgnoreCase("enclosure")) {
            try {
                articleBuilder.imageUrl(attributes.getValue("url"));
            } finally {
                
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            //add Employee object to list
            try {
                articles.add(articleBuilder.build());
            } finally {
                articleBuilder = null;
            }
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        try {
            if (__title) {
                articleBuilder.title(new String(ch, start, length));
            } else if (__description) {
                articleBuilder.description(org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(new String(ch, start, length)));
            } else if (__link) {
                articleBuilder.link(new String(ch, start, length));
            } else if (__guid) {
                articleBuilder.guid(new String(ch, start, length));
            } else if (__category) {
                articleBuilder.tags(new String(ch, start, length));
            }
        } catch (Exception e) {
        } finally {
            __title = __description = __link = __guid = __category = false;
        }
    }
}
