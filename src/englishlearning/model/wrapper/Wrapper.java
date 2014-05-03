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
public class Wrapper<T> {
    private final T rawData;
    private WrapperProperty property;
    
    public Wrapper(T data) {
        rawData = data;
    }
    
    public T getRawData() {
        return rawData;
    }

    public WrapperProperty getProperty() {
        return property;
    }

    public void setProperty(WrapperProperty property) {
        this.property = property;
    }
    
    private boolean __changed;
    boolean isChanged() {
        return __changed;
    }
    void setChanged(boolean __changed) {
        this.__changed = __changed;
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
