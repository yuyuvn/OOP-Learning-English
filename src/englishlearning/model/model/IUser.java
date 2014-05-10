/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.PlayState;
import englishlearning.model.ReadList;
import englishlearning.model.UserInfo;

/**
 *
 * @author Clicia
 */
public interface IUser extends IWrapper {
    UserInfo getUser();
    
    void addPoint(Integer point);
    void addReadlistPoint(String guid, Integer point);
    void addReadlistPoint(PlayState playState);
    
    // Getter & Setter
    String getName();
    void setName(String name);
    String getLastUsed();
    void setLastUsed(String lastUsed);
    ReadList getReadList();
    void setReadList(ReadList readList);
    PlayState getPlayState();
    void setPlayState(PlayState playState);
    Integer getPoint();
    void setPoint(Integer point);
}
