/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.Map;

/**
 *
 * @author Clicia
 */
public class ReadList extends java.util.LinkedHashMap<String,Double> {

    public ReadList(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ReadList(int initialCapacity) {
        super(initialCapacity);
    }

    public ReadList() {
    }

    public ReadList(Map<? extends String, ? extends Double> m) {
        super(m);
    }

    public ReadList(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }
    
}
