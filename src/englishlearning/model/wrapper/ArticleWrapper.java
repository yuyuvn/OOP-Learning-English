/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Article;
import englishlearning.model.ArticleBuilder;
import englishlearning.model.Tags;
import englishlearning.model.model.IArticle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class ArticleWrapper<T extends Article> extends Wrapper<T> implements IArticle {

    public ArticleWrapper(T data) {
        super(data);
    }
    
    public ArticleWrapper() {
        super((T)ArticleBuilder.create().guid("").build());
    }
    
    @Override
    public Article getArticle() {
        return getRawData();
    }

    private transient Image __thumb = null;
    @Override
    public Image getThumbnail(boolean cache) {
        if (__thumb == null) {
            __thumb = new Image(getRawData().getImageUrl(), 0.0, 0.0, false, false, cache);
        }
        
        return __thumb;
    }
    @Override
    public Image getThumbnail() {
        return getThumbnail(true);
    }
    
    // for Serialization
    private LocalDate _date;
    public LocalDate getDate() {return _date;}
    private void writeObject(ObjectOutputStream out) throws IOException {
        // avoid different file format, we save date creation file in object
        _date = LocalDate.now();
        out.defaultWriteObject();
        BufferedImage imageBuffer = SwingFXUtils.fromFXImage(__thumb, null);
        ImageIO.write(imageBuffer, "jpg", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        __thumb = SwingFXUtils.toFXImage(ImageIO.read(in), null); 
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    @Override
    public String getGuid(){
        return getArticle().getGuid();
    }
    @Override
    public void setGuid(String guid){
        getArticle().setGuid(guid);
    }
    @Override
    public String getTitle(){
        return getArticle().getTitle();
    }
    @Override
    public void setTitle(String title){
        getArticle().setTitle(title);
    }
    @Override
    public String getDescription(){
        return getArticle().getDescription();
    }
    @Override
    public void setDescription(String description){
        getArticle().setDescription(description);
    }
    @Override
    public String getLink(){
        return getArticle().getLink();
    }
    @Override
    public void setLink(String link){
        getArticle().setLink(link);
    }
    @Override
    public Tags getTags(){
        return getArticle().getTags();
    }
    @Override
    public void setTags(Tags tags){
        getArticle().setTags(tags);
    }
    @Override
    public String getImageUrl(){
        return getArticle().getImageUrl();
    }
    @Override
    public void setImageUrl(String imageUrl){
        getArticle().setImageUrl(imageUrl);
    }
    @Override
    public String getContent(){
        return getArticle().getContent();
    }
    @Override
    public void setContent(String content){
        getArticle().setContent(content);
    }
//</editor-fold>
}
