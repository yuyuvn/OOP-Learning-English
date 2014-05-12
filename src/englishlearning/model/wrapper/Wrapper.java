/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.model.IWrapper;
import englishlearning.model.property.WrapperProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class Wrapper<T> implements IWrapper, Serializable {
    private final T rawData;
    private transient List<WrapperProperty> _properties;
    private List<WrapperProperty> properties() { 
        if (_properties == null) _properties = Collections.synchronizedList(new ArrayList<>());
        return _properties;
    };
    private transient boolean __changed;
    
    public Wrapper(T data) {
        rawData = data;
    }
    
    protected T getRawData() {
        return rawData;
    }

    @Override
    public List<WrapperProperty> getProperties() {
        // make a clone to avoid conflict
        List<WrapperProperty> clone = new ArrayList(properties());
        return clone;
    }

    @Override
    public void addProperty(WrapperProperty property) {
        this.properties().add(property);
    }
        
    @Override
    public void removeProperty(WrapperProperty property) {
        this.properties().remove(property);
    }

    @Override
    public void fireValueChangedEvent() {
        __changed = true;
        getProperties().forEach(p -> p.fireValueChangedEvent());
        __changed = false;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (__changed) return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
