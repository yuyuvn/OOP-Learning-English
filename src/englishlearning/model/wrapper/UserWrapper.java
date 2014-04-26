/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.UserInfo;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class UserWrapper<T extends UserInfo> extends Wrapper<T> {
    public UserWrapper(T data) {
        super(data);
    }
    
    public String getWelcomeMessage() {
        return "Welcome " + getRawData().name;
    }
}
