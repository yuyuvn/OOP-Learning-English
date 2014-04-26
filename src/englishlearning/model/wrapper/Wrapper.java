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
    
    public Wrapper(T data) {
        rawData = data;
    }
    
    public T getRawData() {
        return rawData;
    }
}
