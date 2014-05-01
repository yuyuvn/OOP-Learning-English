/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;


/**
 *
 * @author Clicia
 * @param <T>
 */
public class WrapperProperty<T extends Wrapper> extends javafx.beans.property.SimpleObjectProperty<T> {

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
        getValue().setChanged(true);
        super.fireValueChangedEvent();
        getValue().setChanged(false);
    }
}
