/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.UsersList;

/**
 *
 * @author Clicia
 */
public class DataInDisk {
    private final static String PATHUL = ""; // TODO: set default path
    
    public static UsersList getUsersList(String dataPath) {
        // TODO: read data from file
        // http://www.tutorialspoint.com/java/java_serialization.htm
        return new UsersList();
    }
    
    public static UsersList getUsersList() {
        return getUsersList(PATHUL);
    }
    
    public static void saveUsersList(UsersList data, String dataPath) {
        // TODO: write data to file
    }
    
    public static void saveUsersList(UsersList data) {
        saveUsersList(data, PATHUL);
    }
}
