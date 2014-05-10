/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.property;

import englishlearning.model.model.IWrapper;


/**
 *
 * @author Clicia
 * @param <T>
 */
public class WrapperProperty<T extends IWrapper> extends javafx.beans.property.SimpleObjectProperty<T> implements ReadOnlyWrapperProperty<T> {

    public WrapperProperty() {
    }

    public WrapperProperty(T initialValue) {
        super(initialValue);
    }

    public WrapperProperty(Object bean, String name) {
        super(bean, name);
    }

    public WrapperProperty(Object bean, String name, T initialValue) {
        super(bean, name, initialValue);
    }
    
    @Override
    public void fireValueChangedEvent() {
        super.fireValueChangedEvent();
    }
    
    @Override
    public void set(T value) {
        T oldValue = get();
        if (oldValue != null) oldValue.removeProperty(this);
        if (value != null) value.addProperty(this);
        super.set(value);
    }
}
