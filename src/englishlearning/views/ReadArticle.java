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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        popOver.show(this, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
        setSelectedText(link.getText());
    }
}
