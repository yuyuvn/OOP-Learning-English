/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Article;
import englishlearning.model.PlayState;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class PlayStateWrapper<T extends PlayState> extends Wrapper<T> {    
    public PlayStateWrapper(T data) {
        super(data);
    }
}
