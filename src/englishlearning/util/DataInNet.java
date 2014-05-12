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
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 *
 * @author Clicia
 */
public class DataInNet {
    private final static String URL_RSS = "http://learningenglish.voanews.com/api/epiqq";
    private final static String URL_DIC = "http://tratu.soha.vn/dict/en_vn/";
    
    public static Articles getListArticle(String urlString) throws Exception {
        URL url = new URL(urlString);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        ArticlesListHandler handler = new ArticlesListHandler();
        
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();
        xr.setContentHandler(handler);
        xr.parse(new InputSource(url.openStream()));
        
        
        return handler.getArticles();
    }
    
    public static Articles getListArticle() throws Exception {
        return getListArticle(URL_RSS);
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
    public static String getMean(String word){
        try {
            URL url = new URL(URL_DIC+word);
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
                buf.append((char) ch);
            }
            String data = buf.toString().replaceAll("</*a.*?>", "");
            Matcher m2 = Pattern.compile("<h5> <span class=\"mw-headline\">([\\w \\-,\\(\\);]+)<", Pattern.UNICODE_CHARACTER_CLASS).matcher(data);
            while ( m2.find() ){
                String match = m2.group(1);
                if (!Pattern.compile("\\w",Pattern.UNICODE_CHARACTER_CLASS).matcher(match).find()) {
                    continue;
                }
                if (match.equals("adjective")) return null;
                if (match.endsWith("từ") && m2.find()) match = m2.group(1);
                return match;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(DataInNet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataInNet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
