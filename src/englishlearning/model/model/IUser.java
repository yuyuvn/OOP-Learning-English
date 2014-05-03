/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.UserInfo;

/**
 *
 * @author Clicia
 */
public interface IUser extends IWrapper {
    public UserInfo getUser();
    public String getWelcomeMessage();
    public String getNumberReadedMessage();
}
