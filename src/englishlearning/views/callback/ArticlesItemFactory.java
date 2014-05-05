/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views.callback;

import englishlearning.controls.ListViewEx;
import englishlearning.model.model.IArticle;
import englishlearning.model.property.WrapperProperty;
import englishlearning.views.ArticlesItem;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author Clicia
 * @param <V>
 * @param <C>
 */
public class ArticlesItemFactory<V,C> implements Callback<ListView<?>,ListCell<?>> {

    @Override
    public ListCell<IArticle> call(ListView param) {
        return new ListCell<IArticle>() {
            @Override
            public void updateItem(IArticle article, boolean empty){
                super.updateItem(article,empty);
                if(article != null) {
                    ArticlesItem data = new ArticlesItem();
                    data.setData(article);
                    setGraphic(data);
                    
                    if (param instanceof ListViewEx) {
                        ListViewEx listView = (ListViewEx) param;
                        data.setOnMouseClicked(e -> {
                            if (e.getClickCount() > 1) {
                                listView.fireSelected();
                            }
                        });
                    }
                }
            }
        };
    }
    
}
