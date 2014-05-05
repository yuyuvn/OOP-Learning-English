/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class UsersList<T extends String> extends HashSet<T> {

    public UsersList() {
    }

    public UsersList(Collection<? extends T> c) {
        super(c);
    }

    public UsersList(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public UsersList(int initialCapacity) {
        super(initialCapacity);
    }
    
}
