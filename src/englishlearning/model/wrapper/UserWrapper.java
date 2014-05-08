/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.PlayState;
import englishlearning.model.ReadList;
import englishlearning.model.UserInfo;
import englishlearning.model.UserInfoBuilder;
import englishlearning.model.model.IUser;

/**
 *
 * @author Clicia
 * @param <T>
 */
public class UserWrapper<T extends UserInfo> extends Wrapper<T> implements IUser {
    public UserWrapper(T data) {
        super(data);
    }
    
    public UserWrapper() {
        super((T) UserInfoBuilder.create().name("").build());
    }
        
    public Integer getReadedArticles() {
        return getRawData().getReadList().size();
    }

    @Override
    public UserInfo getUser() {
        return getRawData();
    }
     @Override
    public String getName(){
        return getUser().getName();
    }
    @Override
    public void setName(String name){
        getUser().setName(name);
    }
    @Override
    public String getLastUsed(){
        return getUser().getLastUsed();
    }
    @Override
    public void setLastUsed(String lastUsed){
        getUser().setLastUsed(lastUsed);
    }
    @Override
    public ReadList getReadList(){
        return getUser().getReadList();
    }
    @Override
    public void setReadList(ReadList readList){
        getUser().setReadList(readList);
    }
    @Override
    public PlayState getPlayState(){
        return getUser().getPlayState();
    }
    @Override
    public void setPlayState(PlayState playState){
        getUser().setPlayState(playState);
    }
    
}
