/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.controls.ListViewEx;
import englishlearning.model.model.IArticle;
import englishlearning.model.property.ReadOnlyWrapper;
import englishlearning.model.property.ReadOnlyWrapperProperty;
import java.util.Collection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.controlsfx.control.textfield.CustomTextField;

/**
 *
 * @author Clicia
 */
public class ArticlesList extends Controller {
    
    @FXML public ListViewEx listView;
    //<editor-fold defaultstate="collapsed" desc="Property articles">
    private ListProperty<IArticle> articles;
    
    public final ObservableList<IArticle> getArticles() {
        return articlesProperty().get();
    }
    
    public final void setArticles(ObservableList<IArticle> value) {
        articlesProperty().set(value);
    }
    
    public final void setArticles(Collection<IArticle> value) {
        setArticles(FXCollections.observableArrayList(value));
    }
    
    public final ListProperty<IArticle> articlesProperty() {
        if (articles == null) articles = new SimpleListProperty<>(this, "articles");
        return articles;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property selectedArticle">
    private ReadOnlyWrapper<IArticle> selectedArticle;
    
    public final IArticle getSelectedArticle() {
        return selectedArticleProperty().get();
    }
    
    private void setSelectedArticle(IArticle value) {
        _selectedArticleProperty().set(value);
    }
    
    public final ReadOnlyWrapperProperty<IArticle> selectedArticleProperty() {
        return _selectedArticleProperty();
    }
    
    private ReadOnlyWrapper<IArticle> _selectedArticleProperty() {
        if (selectedArticle == null) {            
            selectedArticle = new ReadOnlyWrapper<>(this, "selectedArticle");
        }
        return selectedArticle;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property selected">
    public BooleanProperty selectedProperty() {
        return listView.selectedProperty();
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property filterText">
    @FXML private CustomTextField searchField;
    public String getFilterText() { return filterTextProperty().get(); }
    public void setFilterText(String value) { filterTextProperty().set(value); }
    public StringProperty filterTextProperty() { return searchField.textProperty(); }
//</editor-fold>
    
    
    public ArticlesList() {
        _selectedArticleProperty().bind(listView.getSelectionModel().selectedItemProperty());
        /*listView.selectedProperty().addListener((e) -> {
        fireSelected();
        });*/
    }
    
    public void clearSelection() {
        listView.getSelectionModel().clearSelection();
    }
}
