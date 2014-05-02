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
    private ArticleBuilder __articleBuilder;    
    private Field __field = Field.NIL;
    
    public Articles getArticles() {
        return articles;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            __articleBuilder = ArticleBuilder.create();
        } else if (qName.equalsIgnoreCase("title")) {
            __field = Field.TITLE;
        } else if (qName.equalsIgnoreCase("description")) {
            __field = Field.DESCRIPTION;
        } else if (qName.equalsIgnoreCase("link")) {
            __field = Field.LINK;
        } else if (qName.equalsIgnoreCase("guid")) {
            __field = Field.GUID;
        } else if (qName.equalsIgnoreCase("category")) {
            __field = Field.CATEGORY;
        } else if (qName.equalsIgnoreCase("enclosure")) {
            try {
                __articleBuilder.imageUrl(attributes.getValue("url"));
            } finally {
                
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            //add Employee object to list
            try {
                articles.add(__articleBuilder.build());
            } finally {
                __articleBuilder = null;
            }
        }
    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        try {
            switch (__field) {
                case TITLE:
                    __articleBuilder.title(new String(ch, start, length));
                    break;
                case DESCRIPTION:
                    __articleBuilder.description(org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3(new String(ch, start, length)));
                    break;
                case LINK:
                    __articleBuilder.link(new String(ch, start, length));
                    break;
                case GUID:
                    __articleBuilder.guid(new String(ch, start, length));
                    break;
                case CATEGORY:
                    __articleBuilder.tags(new String(ch, start, length));
                    break;
            }
        } catch (Exception e) {
        } finally {
            __field = Field.NIL;
        }
    }    
    
    private enum Field {
        NIL, TITLE, DESCRIPTION, LINK, GUID, CATEGORY
    }
}
