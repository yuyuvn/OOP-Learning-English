/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.model.PlayState;
import englishlearning.model.model.IWord;
import englishlearning.model.wrapper.WordWrapper;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Clicia
 */
public class Result extends Controller implements DataReceivable {
    //<editor-fold defaultstate="collapsed" desc="int score">
    private ReadOnlyIntegerWrapper score;
    
    public int getScore() {
        return _scoreProperty().get();
    }
    
    private void setScore(int value) {
        _scoreProperty().set(value);
    }
    
    public ReadOnlyIntegerProperty scoreProperty() {
        return _scoreProperty().getReadOnlyProperty();
    }
    
    private ReadOnlyIntegerWrapper _scoreProperty() {
        if (score == null) score = new ReadOnlyIntegerWrapper(this, "score", 0);
        return score;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="int total">
    private ReadOnlyIntegerWrapper total;
    
    public int getTotal() {
        return _totalProperty().get();
    }
    
    private void setTotal(int value) {
        _totalProperty().set(value);
    }
    
    public ReadOnlyIntegerProperty totalProperty() {
        return _totalProperty().getReadOnlyProperty();
    }
    
    private ReadOnlyIntegerWrapper _totalProperty() {
        if (total == null) total = new ReadOnlyIntegerWrapper(this, "total", 0);
        return total;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="List<IWord> wordList">
    private ListProperty<IWord> wordList;
    
    public ObservableList<IWord> getWordList() {
        return wordListProperty().get();
    }
    
    private void setWordList(ObservableList<IWord> value) {
        wordListProperty().set(value);
    }
    
    public ListProperty<IWord> wordListProperty() {
        if (wordList == null) wordList = new ReadOnlyListWrapper<>(this, "wordList");
        return wordList;
    }
//</editor-fold>
    
    
    @Override
    public void setData(Object value) {
        try {
            PlayState data = (PlayState) value;
            setTotal(data.size());
            setWordList(FXCollections.observableArrayList(data.stream().flatMap(w -> Stream.of(new WordWrapper(w))).collect(toList())));
            setScore(getWordList().stream().filter(w -> w.isRightAnswer()).collect(toList()).size());
        } catch (ClassCastException e) {
        }
    }
    
}
