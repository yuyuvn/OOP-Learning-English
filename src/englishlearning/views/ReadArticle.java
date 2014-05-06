/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IArticle;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.ArticleWrapper;
import java.awt.MouseInfo;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PopOver;

/**
 *
 * @author Clicia
 */
public class ReadArticle extends Controller implements DataReceivable {
    //<editor-fold defaultstate="collapsed" desc="Property selectedText">
    private ReadOnlyStringWrapper selectedText;
    
    public String getSelectedText() {
        return selectedTextProperty().get();
    }
    
    private void setSelectedText(String value) {
        _selectedTextProperty().set(value);
    }
    
    public ReadOnlyStringProperty selectedTextProperty() {
        return _selectedTextProperty().getReadOnlyProperty();
    }
    
    private ReadOnlyStringWrapper _selectedTextProperty() {
        if (selectedText == null) selectedText = new ReadOnlyStringWrapper(this, "selectedText", "");
        return selectedText;
    }
//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Property wordMean">
    private StringProperty wordMean;
    
    public String getWordMean() {
        return wordMean.get();
    }
    
    public void setWordMean(String value) {
        wordMean.set(value);
    }
    
    public StringProperty wordMeanProperty() {
        if (wordMean == null) wordMean = new SimpleStringProperty(this, "wordMean", "");
        return wordMean;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property article">
    private WrapperProperty<IArticle> article;
    
    public final IArticle getArticle() {
        return articleProperty().get();
    }
    
    public void setArticle(IArticle value) {
        articleProperty().set(value);
    }
    
    public WrapperProperty<IArticle> articleProperty() {
        if (article == null) article = new WrapperProperty<>(this, "article", new ArticleWrapper());
        return article;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Propert parsedContent">
    private StringProperty parsedContent;
    
    public String getParsedContent() {
        return parsedContentProperty().get();
    }
    
    public void setParsedContent(String value) {
        parsedContentProperty().set(value);
    }
    
    public StringProperty parsedContentProperty() {
        if (parsedContent == null) parsedContent = new SimpleStringProperty(this, "parsedContent", "");
        return parsedContent;
    }
//</editor-fold>
    
    
    @FXML private PopOver popOver;
    public PopOver getPopOver() {
        return popOver;
    }

    @Override
    public void setData(Object value) {
        setArticle((IArticle)value);
    }
    
    @FXML private void onClick(ActionEvent event) {
        Hyperlink link = (Hyperlink)event.getSource();
        showPopOver();
        setSelectedText(link.getText());
    }
    
    private void showPopOver() {
        popOver.show(this, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
        popOver.onShownProperty().addListener(e -> {
            EventHandler<MouseEvent> hd = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    popOver.hide();
                    Scene scene = (Scene)mouseEvent.getSource();
                    scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }
            };
            getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, hd);
        });
    }
}
