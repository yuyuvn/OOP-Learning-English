/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.WordBuilder;
import englishlearning.model.model.IArticle;
import englishlearning.model.model.IWord;
import englishlearning.model.property.ReadOnlyWrapper;
import englishlearning.model.property.ReadOnlyWrapperProperty;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.ArticleWrapper;
import englishlearning.model.wrapper.WordWrapper;
import englishlearning.util.Lookup;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

/**
 *
 * @author Clicia
 */
public class ReadArticle extends Controller implements DataReceivable {
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
    //<editor-fold defaultstate="collapsed" desc="Property parsedContent">
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
    //<editor-fold defaultstate="collapsed" desc="Property selectedWord">
    private ReadOnlyWrapper<IWord> selectedWord;
    
    public final IWord getSelectedWord() {
        return selectedWordProperty().get();
    }
    
    private void setSelectedWord(IWord value) {
        _selectedWordProperty().set(value);
    }
    
    public final ReadOnlyWrapperProperty<IWord> selectedWordProperty() {
        return _selectedWordProperty().getReadOnlyProperty();
    }
    
    private ReadOnlyWrapper<IWord> _selectedWordProperty() {
        if (selectedWord == null) selectedWord = new ReadOnlyWrapper<>(this, "selectedWord");
        return selectedWord;
    }
//</editor-fold>

    @Override
    public void setData(Object value) {
        try {
            setArticle((IArticle)value);
        } catch (ClassCastException e) {
        }
    }
    
    @FXML private void onClick(ActionEvent event) {
        Hyperlink link = (Hyperlink)event.getSource();
        setSelectedWord(new WordWrapper(WordBuilder.create().word(link.getText()).build()));
    }
}
