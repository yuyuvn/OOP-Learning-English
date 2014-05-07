/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.UserInfo;
import englishlearning.model.Word;
import java.util.Set;

/**
 *
 * @author Clicia
 */
public interface IUser extends IWrapper {
    public UserInfo getUser();
    public Set<Word> getPlayState();
}
