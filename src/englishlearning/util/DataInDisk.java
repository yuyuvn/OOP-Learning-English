/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.UserInfo;
import englishlearning.model.UserInfoBuilder;
import englishlearning.model.UsersList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clicia
 */
public class DataInDisk {
    private final static String PATH_USERSLIST = "data/users.bin";
    private final static String PATH_USERS = "data/i%s.bin";
    
    public static String getRelativePath(String path) {
        return new File(System.getProperty("user.dir"), path).getPath();
    }
    
    public static UsersList getUsersList(String dataPath) {
        UsersList list = null;
        try (FileInputStream fileIn = new FileInputStream(dataPath); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            list = (UsersList) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataInDisk.class.getName()).log(Level.WARNING, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            
        } finally {
            if (list == null) list = new UsersList();
        }
        return list;
    }
    
    public static UsersList getUsersList() {
        return getUsersList(getRelativePath(PATH_USERSLIST));
    }
    
    public static void saveUsersList(UsersList data, String dataPath) {
        createIfNotExists(dataPath);
        
        try (FileOutputStream fileOut = new FileOutputStream(dataPath); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataInDisk.class.getName()).log(Level.WARNING, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataInDisk.class.getName()).log(Level.WARNING, null, ex);
        }
    }
    
    public static void saveUsersList(UsersList data) {
        saveUsersList(data, getRelativePath(PATH_USERSLIST));
    }
    
    public static boolean createIfNotExists(String path) {        
        File fileHandler = new File(path);
        if(!fileHandler.exists()) {
            try {
                fileHandler.getParentFile().mkdirs();
                fileHandler.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DataInDisk.class.getName()).log(Level.WARNING, null, ex);
                return false;
            }
        }
        return true;
    }
    
    public static UserInfo getUserInfo(String username) {
        // TODO
        UserInfo user = UserInfoBuilder.create().name(username).build();
        
        return user;
    }
}
