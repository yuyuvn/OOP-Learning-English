/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views.callback;

import englishlearning.model.model.IArticle;
import englishlearning.model.model.IWord;
import englishlearning.views.ResultItem;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Clicia
 */
public class ResultItemFactory<V,C> implements Callback<ListView<?>,ListCell<?>> {

    @Override
    public ListCell<IWord> call(ListView param) {
        return new ListCell<IWord>() {
            @Override
            public void updateItem(IWord word, boolean empty){
                super.updateItem(word,empty);
                
                if(word != null && !empty) {
                    ResultItem data = new ResultItem();
                    data.setData(word);
                    setGraphic(data);
                    
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }
    
}
