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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author Clicia
 */
public class DataInNet {
    private final static String URL_RSS = "http://learningenglish.voanews.com/api/epiqq";
    
    public static Articles<Article> getListArticle(String urlString) throws MalformedURLException{
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
    
    public static Articles<Article> getListArticle() throws MalformedURLException {
        Articles<Article> value;
        value = DataInDisk.getData(DataInDisk.getRelativePath("data/debug/cacheAL.bin"));
        if (value == null) {
            value = getListArticle(URL_RSS);
            DataInDisk.saveData(value, DataInDisk.getRelativePath("data/debug/cacheAL.bin"));
        }
        return value;
    }
    
    public static Article getArticle(Article article) throws IOException {
        // TODO: load url from article.link then get content and set to it        
        // http://learningenglish.voanews.com/content/words-and-their-stories-belittle/1578052.html
        
        Document doc = Jsoup.connect(article.getLink()).get();
        Element data = doc.select(".zoomMe").first();
        
        // Remove garbage
        data.select("ul").remove();
        data.select("div").stream().filter(d -> d.select("br").isEmpty()).forEach(e -> e.remove());
        // Convert br & p to \n
        data.select("br").append("\\n");
        data.select("p").append("\\n\\n");
        
        article.setContent(data.text().replaceAll("\\\\n", "\n").trim());
        return article;
    }
    public static String getMean (String word) throws MalformedURLException, IOException{
        URL url = new URL("http://tratu.soha.vn/dict/en_vn/yellow");
        URLConnection con = url.openConnection();
        Pattern p = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m = p.matcher(con.getContentType());
        String charset = m.matches() ? m.group(1) : "UTF-8";
        Reader r = new InputStreamReader(con.getInputStream(), charset);
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0)
        break;
            StringBuilder append = buf.append((char) ch);
        }
        String str = buf.toString();
        //System.out.println(str);
        Pattern p2 = Pattern.compile("<h5> <span class=\"mw-headline\">(\\w+)<", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m2 = p2.matcher(str);
           if( m2.find() ){
            String match = m2.group(1);
            return match;
            }
           return null;
    }
}
