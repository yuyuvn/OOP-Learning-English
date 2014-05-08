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
public class Articles extends java.util.ArrayList<Article> {

    public Articles(int initialCapacity) {
        super(initialCapacity);
    }

    public Articles() {
    }

    public Articles(Collection<? extends Article> c) {
        super(c);
    }
}
