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
    public UserInfo getUser();
    public String getName();
    public void setName(String name);
    public String getLastUsed();
    public void setLastUsed(String lastUsed);
    public ReadList getReadList();
    public void setReadList(ReadList readList);
    public PlayState getPlayState();
    public void setPlayState(PlayState playState);
}
