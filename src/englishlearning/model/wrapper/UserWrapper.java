/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.model.IUser;
import englishlearning.model.UserInfo;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class UserWrapper<T extends UserInfo> extends Wrapper<T> implements IUser {
    public UserWrapper(T data) {
        super(data);
    }
    
    @Override
    public String getWelcomeMessage() {
        return "Welcome " + getRawData().name;
    }
    
    @Override
    public String getNumberReadedMessage() {
        return String.format("You have readed %s articles", getReadedArticles());
    }
    
    public int getReadedArticles() {
        return getRawData().readList.size();
    }

    @Override
    public UserInfo getUser() {
        return getRawData();
    }
}
