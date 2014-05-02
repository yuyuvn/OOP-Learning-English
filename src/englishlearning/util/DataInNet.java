/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.Article;
import englishlearning.model.Articles;
import englishlearning.util.handler.ArticlesListHandler;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author Clicia
 */
public class DataInNet {
    private final static String URL_RSS = "http://learningenglish.voanews.com/api/epiqq";
    
    public static Articles getListArticle(String urlString) throws MalformedURLException{
        URL url = new URL(urlString);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        ArticlesListHandler handler = new ArticlesListHandler();
        
        try {
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            xr.setContentHandler(handler);
            xr.parse(new InputSource(url.openStream()));            
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            Logger.getLogger(DataInNet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return handler.getArticles();
    }
    
    public static Articles getListArticle() throws MalformedURLException {
        return getListArticle(URL_RSS);
    }
    
    public static Article getArticle(Article article) throws IOException {
        // TODO: load url from article.link then get content and set to it        
        // http://learningenglish.voanews.com/content/words-and-their-stories-belittle/1578052.html
        return article;
    }
}
