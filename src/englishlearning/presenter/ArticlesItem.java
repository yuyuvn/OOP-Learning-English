/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.model.IArticle;
import englishlearning.model.wrapper.WrapperProperty;

/**
 *
 * @author Clicia
 */
public class ArticlesItem extends Controller {
    //<editor-fold defaultstate="collapsed" desc="Property data">
    private WrapperProperty<IArticle> data;
    
    public IArticle getData() {
        return dataProperty().get();
    }
    
    public void setData(IArticle value) {
        dataProperty().set(value);
    }
    
    public WrapperProperty<IArticle> dataProperty() {
        if (data == null) data = new WrapperProperty<>(this,"data");
        return data;
    }
//</editor-fold>
    
    
}
