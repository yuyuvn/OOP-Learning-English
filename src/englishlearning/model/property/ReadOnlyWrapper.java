/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.property;

import com.sun.javafx.binding.ExpressionHelper;
import englishlearning.model.model.IWrapper;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class ReadOnlyWrapper<T extends IWrapper> extends WrapperProperty<T> {

    private ReadOnlyPropertyImpl readOnlyProperty;
    
    public ReadOnlyWrapper() {
    }

    public ReadOnlyWrapper(T initialValue) {
        super(initialValue);
    }

    public ReadOnlyWrapper(Object bean, String name) {
        super(bean, name);
    }

    public ReadOnlyWrapper(Object bean, String name, T initialValue) {
        super(bean, name, initialValue);
    }
    
    public ReadOnlyWrapperProperty<T> getReadOnlyProperty() {
        if (readOnlyProperty == null) {
            readOnlyProperty = new ReadOnlyPropertyImpl();
        }
        return readOnlyProperty;
    }

    /**
     * {@inheritDoc}
     * @param listener
     */
    @Override
    public void addListener(InvalidationListener listener) {
        getReadOnlyProperty().addListener(listener);
    }

    /**
     * {@inheritDoc}
     * @param listener
     */
    @Override
    public void removeListener(InvalidationListener listener) {
        if (readOnlyProperty != null) {
            readOnlyProperty.removeListener(listener);
        }
    }

    /**
     * {@inheritDoc}
     * @param listener
     */
    @Override
    public void addListener(ChangeListener<? super T> listener) {
        getReadOnlyProperty().addListener(listener);
    }

    /**
     * {@inheritDoc}
     * @param listener
     */
    @Override
    public void removeListener(ChangeListener<? super T> listener) {
        if (readOnlyProperty != null) {
            readOnlyProperty.removeListener(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fireValueChangedEvent() {
        if (readOnlyProperty != null) {
            readOnlyProperty.fireValueChangedEvent();
        }
    }

    private class ReadOnlyPropertyImpl implements ReadOnlyWrapperProperty<T> {
        
        private ExpressionHelper<T> helper = null;
        
        @Override
        public T get() {
            return ReadOnlyWrapper.this.get();
        }

        @Override 
        public void addListener(InvalidationListener listener) {
            helper = ExpressionHelper.addListener(helper, this, listener);
        }

        @Override 
        public void removeListener(InvalidationListener listener) {
            helper = ExpressionHelper.removeListener(helper, listener);
        }
        
        @Override
        public void addListener(ChangeListener<? super T> listener) {
            helper = ExpressionHelper.addListener(helper, this, listener);
        }

        @Override 
        public void removeListener(ChangeListener<? super T> listener) {
            helper = ExpressionHelper.removeListener(helper, listener);
        }
        
        @Override
        public void fireValueChangedEvent() {
            ExpressionHelper.fireValueChangedEvent(helper);
        }
        
        @Override
        public Object getBean() {
            return ReadOnlyWrapper.this.getBean();
        }

        @Override
        public String getName() {
            return ReadOnlyWrapper.this.getName();
        }

        @Override
        public T getValue() {
            return get();
        }
    };
}
