/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.property;

import englishlearning.model.model.IWrapper;
import javafx.beans.property.ReadOnlyProperty;

/**
 *
 * @author Clicia
 * @param <T>
 */
public interface ReadOnlyWrapperProperty<T extends IWrapper> extends ReadOnlyProperty<T> {
    void fireValueChangedEvent();
    
    T get();
}
