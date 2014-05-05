/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import javafx.beans.property.ObjectProperty;

/**
 *
 * @author Clicia
 */
public interface DataReceivable {
    void setData(Object value);
    ObjectProperty dataProperty();
}
