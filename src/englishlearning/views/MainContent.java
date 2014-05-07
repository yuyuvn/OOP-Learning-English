/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.model.IUser;
import englishlearning.model.property.WrapperProperty;
import englishlearning.model.wrapper.UserWrapper;
import java.awt.MouseInfo;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.controlsfx.control.PopOver;

/**
 *
 * @author Clicia
 */
public class MainContent extends Controller {
    @FXML ArticlesList articlesList;
    public ArticlesList getArticlesList() {return articlesList;}
    @FXML ReadArticle readArticle;
    public ReadArticle getReadArticle() {return readArticle;}
    
    @FXML private PopOver popOver;
    @FXML private ListView listView;
    public ListView getListView() {
        return listView;
    }
    
    //<editor-fold defaultstate="collapsed" desc="IUser User">
    private WrapperProperty<IUser> user;
    public final IUser getUser() { return userProperty().get(); }
    public final void setUser(IUser value) { userProperty().set(value); }
    public final WrapperProperty<IUser> userProperty() { 
        if (user == null) user = new WrapperProperty(this, "user", new UserWrapper());
        return user; 
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Object binding data">
    private ObjectProperty data;
    
    public final Object getData() {
        return dataProperty().get();
    }
    
    public final void setData(Object value) {
        dataProperty().set(value);
    }
    
    public final ObjectProperty dataProperty() {
        if (data == null) data = new SimpleObjectProperty(this, "data");
        return data;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="List<String> wordList">
    private ListProperty<String> wordList;
    
    public ObservableList<String> getWordList() {
        return wordList.get();
    }
    
    public void setWordList(ObservableList<String> value) {
        wordList.set(value);
    }
    
    public ListProperty wordListProperty() {
        if (wordList == null) wordList = new ReadOnlyListWrapper<>(this, "wordList", FXCollections.observableArrayList());
        return wordList;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Boolean canTest">
    private BooleanProperty canTest;
    
    public boolean isCanTest() {
        return canTest.get();
    }
    
    public void setCanTest(boolean value) {
        canTest.set(value);
    }
    
    public BooleanProperty canTestProperty() {
        if (canTest == null) canTest = new SimpleBooleanProperty(this, "canTest", false);
        return canTest;
    }
//</editor-fold>
    
    
    private EventHandler onReturn;
    public void setOnReturn(EventHandler eventHandler) {
        onReturn = eventHandler;
    }    
    public void onReturn(ActionEvent event) {
        if (onReturn != null) onReturn.handle(event);
    }
    
    private EventHandler onDoTest;
    public void setOnDoTest(EventHandler eventHandler) {
        onDoTest = eventHandler;
    }    
    @FXML private void onDoTest(ActionEvent event) {
        if (onDoTest != null) onDoTest.handle(event);
    }
    
    public void showPopOver() {
        popOver.show(this, MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
    }
    public void hidePopOver() {
        popOver.hide();
    }
}
