/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IArticle;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.ArticleWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

/**
 *
 * @author Clicia
 */
public class ReadArticle extends Controller {
    //<editor-fold defaultstate="collapsed" desc="Property selectedText">
    private ReadOnlyStringWrapper selectedText;
    
    public String getSelectedText() {
        return selectedTextProperty().get();
    }
    
    public ReadOnlyStringProperty selectedTextProperty() {
        return _selectedTextProperty().getReadOnlyProperty();
    }
    
    private ReadOnlyStringWrapper _selectedTextProperty() {
        if (selectedText == null) selectedText = new ReadOnlyStringWrapper();
        return selectedText;
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
}
