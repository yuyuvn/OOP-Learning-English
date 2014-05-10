/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.Collection;

/**
 *
 * @author Clicia
 */
public class Options extends java.util.HashSet<String>{

    public Options() {
    }

    public Options(Collection<? extends String> c) {
        super(c);
    }

    public Options(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public Options(int initialCapacity) {
        super(initialCapacity);
    }
}
