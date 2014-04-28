/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.builder;

import englishlearning.model.PlayState;
import englishlearning.model.ReadList;
import englishlearning.model.UserInfo;
import java.util.Date;

/**
 *
 * @author Clicia
 */
public class UserInfoBuilder {
    private final UserInfo data = new UserInfo();
    
    public UserInfoBuilder() {
        
    }
    
    public UserInfoBuilder(String name) {
        data.name = name;
    }
    
    public UserInfoBuilder name(String name) {
        data.name = name;
        return this;
    }
    
    public UserInfoBuilder lastUsed(Date lastUsed) {
        data.lastUsed = lastUsed;
        return this;
    }
    
    public UserInfoBuilder readList(ReadList readList) {
        data.readList = readList;
        return this;
    }
    
    public UserInfoBuilder playState(PlayState playState) {
        data.playState = playState;
        return this;
    }
    
    public UserInfo build() {
        if (data.name == null) throw new IllegalArgumentException("Name is not setted");
        return data;
    }
}
