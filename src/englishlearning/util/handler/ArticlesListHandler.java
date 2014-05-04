/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util.handler;

import englishlearning.model.ArticleBuilder;
import englishlearning.model.Articles;
import static org.apache.commons.lang3.StringEscapeUtils.unescapeHtml3;
import org.apache.commons.lang3.StringUtils;
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
    private StringBuilder __buffer;
    private Field __field = Field.NIL;
    
    public Articles getArticles() {
        return articles;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            __articleBuilder = ArticleBuilder.create();
        } else if (__articleBuilder != null) {
            if (qName.equalsIgnoreCase("title")) {
                __field = Field.TITLE;
                __buffer = new StringBuilder();
            } else if (qName.equalsIgnoreCase("description")) {
                __field = Field.DESCRIPTION;
                __buffer = new StringBuilder();
            } else if (qName.equalsIgnoreCase("link")) {
                __field = Field.LINK;
                __buffer = new StringBuilder();
            } else if (qName.equalsIgnoreCase("guid")) {
                __field = Field.GUID;
                __buffer = new StringBuilder();
            } else if (qName.equalsIgnoreCase("category")) {
                __field = Field.CATEGORY;
                __buffer = new StringBuilder();
            } else if (qName.equalsIgnoreCase("enclosure")) {
                try {
                    __articleBuilder.imageUrl(attributes.getValue("url"));
                } finally {
                }
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            try {
                articles.add(__articleBuilder.build());
            } finally {
                __articleBuilder = null;
            }
        } else if (__articleBuilder != null) {
            if (qName.equalsIgnoreCase("title")) {
                __articleBuilder.title(__buffer.toString());
            } else if (qName.equalsIgnoreCase("description")) {
                String data = unescapeHtml3(__buffer.toString()).replaceAll("\\[.*?\\]", "").replace(String.valueOf((char) 160), " ").trim();
                __articleBuilder.description(StringUtils.trim(StringUtils.join(data.split(" ", 101)," ", 0, 99)) + "...");
                __articleBuilder.content(data);
            } else if (qName.equalsIgnoreCase("link")) {
                __articleBuilder.link(__buffer.toString());
            } else if (qName.equalsIgnoreCase("guid")) {
                __articleBuilder.guid(__buffer.toString());
            } else if (qName.equalsIgnoreCase("category")) {
                __articleBuilder.tags(__buffer.toString());
            }
        }
        __field = Field.NIL;
    }
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        try {
            switch (__field) {
                case TITLE: case DESCRIPTION: case LINK: case GUID: case CATEGORY:
                    __buffer.append(new String(ch, start, length));
                    
                    break;
            }
        } finally {
            // __field = Field.NIL;
        }
    }    
    
    private enum Field {
        NIL, TITLE, DESCRIPTION, LINK, GUID, CATEGORY
    }
}
