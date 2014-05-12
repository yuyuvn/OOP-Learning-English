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
import java.awt.MouseInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import org.controlsfx.control.PopOver;

/**
 *
 * @author Clicia
 */
public class ReadArticle extends Controller {
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
    
    
    //<editor-fold defaultstate="collapsed" desc="PopOver popOver">
    @FXML private PopOver popOver;
    public void showPopOver() {
        popOver.show(this, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
        popOver.detach();
    }
    public void hidePopOver() {
        popOver.hide();
    }
//</editor-fold>
    
    @FXML private void onClick(ActionEvent event) {
        Hyperlink link = (Hyperlink)event.getSource();
        setSelectedWord(new WordWrapper(WordBuilder.create().word(link.getText()).build()));
    }
}
