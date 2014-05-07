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
public class UsersList extends java.util.HashSet<String> {

    public UsersList() {
    }

    public UsersList(Collection<? extends String> c) {
        super(c);
    }

    public UsersList(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public UsersList(int initialCapacity) {
        super(initialCapacity);
    }
    
}
