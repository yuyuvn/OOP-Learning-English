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
    
    @SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
    public UserInfoBuilder() {
        
    }
    @SuppressWarnings("unchecked")
    public static UserInfoBuilder create() {
        return new UserInfoBuilder();
    }
    
    @SuppressWarnings("unchecked")
    public UserInfoBuilder name(String name) {
        data.name = name;
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfoBuilder lastUsed(Date lastUsed) {
        data.lastUsed = lastUsed;
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfoBuilder readList(ReadList readList) {
        data.readList = readList;
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfoBuilder playState(PlayState playState) {
        data.playState = playState;
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfo build() {
        if (data.name == null) throw new RuntimeException("Name is not set");
        if (data.readList == null) data.readList = new ReadList();
        return data;
    }
}
