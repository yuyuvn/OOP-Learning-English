/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.UsersList;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Clicia
 */
public class DataInDisk {
    private final static String PATHUL = "data/users.bin"; // TODO: set default path
    
    public static String getRelativePath(String path) {
        return new File(System.getProperty("user.dir"), path).getPath();
    }
    
    public static UsersList getUsersList(String dataPath) {
        UsersList list = new UsersList();
        try (FileInputStream fileIn = new FileInputStream(dataPath); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            list = (UsersList) in.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataInDisk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DataInDisk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static UsersList getUsersList() {
        return getUsersList(getRelativePath(PATHUL));
    }
    
    public static void saveUsersList(UsersList data, String dataPath) {
        File fileHandler = new File(dataPath);
        if(!fileHandler.exists()) {
            try {
                fileHandler.getParentFile().mkdirs();
                fileHandler.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DataInDisk.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        try (FileOutputStream fileOut = new FileOutputStream(dataPath); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataInDisk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataInDisk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void saveUsersList(UsersList data) {
        saveUsersList(data, getRelativePath(PATHUL));
    }
}
