/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

/**
 *
 * @author Clicia
 */
public class UserInfoBuilder {
    private final UserInfo data = new UserInfo();
    
    @SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
    protected UserInfoBuilder() {
    }
    @SuppressWarnings("unchecked")
    public static UserInfoBuilder create() {
        return new UserInfoBuilder();
    }
        
    @SuppressWarnings("unchecked")
    public UserInfoBuilder name(String name) {
        data.setName(name);
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfoBuilder lastUsed(String lastUsed) {
        data.setLastUsed(lastUsed);
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfoBuilder readList(ReadList readList) {
        data.setReadList(readList);
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfoBuilder playState(PlayState playState) {
        data.setPlayState(playState);
        return this;
    }
    @SuppressWarnings("unchecked")
    public UserInfoBuilder point(Integer point) {
        data.setPoint(point);
        return this;
    }
    
    public UserInfo build() {
        if (data.getName() == null) throw new RuntimeException("Name is not set");
        if (data.getReadList() == null) readList(new ReadList());
        if (data.getPoint() == null) data.setPoint(0);
        return data;
    }
}
