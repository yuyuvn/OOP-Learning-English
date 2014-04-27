/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.UsersList;
import java.io.*;

/**
 *
 * @author Clicia
 */
public class DataInDisk {
    private final static String PATHUL = ""; // TODO: set default path
    
    public static UsersList getUsersList(String dataPath) throws FileNotFoundException, IOException, ClassNotFoundException {
        UsersList list = null;
        FileInputStream fileIn = new FileInputStream(dataPath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        list = (UsersList) in.readObject();
        in.close();
        fileIn.close();
        // TODO: read data from file
        // http://www.tutorialspoint.com/java/java_serialization.htm
        return new UsersList();
    }
    
    public static UsersList getUsersList() throws IOException, FileNotFoundException, ClassNotFoundException {
        return getUsersList(PATHUL);
    }
    
    public static void saveUsersList(UsersList data, String dataPath) throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(dataPath);
        ObjectOutputStream out  = new ObjectOutputStream(fileOut);
        out.writeObject(data);
        out.close();
        fileOut.close();
        // TODO: write data to file
    }
    
    public static void saveUsersList(UsersList data) throws IOException {
        saveUsersList(data, PATHUL);
    }
}
