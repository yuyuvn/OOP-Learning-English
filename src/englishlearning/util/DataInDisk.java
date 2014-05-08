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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import englishlearning.model.Dictionary;
import java.util.Scanner;

/**
 *
 * @author Clicia
 */
public class DataInDisk {
    private final static String PATH_USERSLIST = "data/users.bin";
    private final static String PATH_USER = "data/i%s.bin";
    private final static String PATH_DICT = "data/dict.txt";
        
    public static UsersList getUsersList() {
        UsersList userList = getData(getRelativePath(PATH_USERSLIST));
        return userList != null ? userList : new UsersList();
    }
    
    public static void saveUsersList(UsersList data) {
        saveData(data, getRelativePath(PATH_USERSLIST));
    }
    
    public static UserInfo getUserInfo(String username) {
        UserInfo user = getData(getRelativePath(String.format(PATH_USER, MD5(username))));
        return user != null ? user : UserInfoBuilder.create().name(username).build();
    }
    
    public static void saveUserInfo(UserInfo user) {
        saveData(user,getRelativePath(String.format(PATH_USER, MD5(user.getName()))));
    }
    
    
    
    /*
    * Support method
    */
    static <T> T getData(String dataPath) {
        try (FileInputStream fileIn = new FileInputStream(dataPath); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (T) in.readObject();
        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {            
        }
        return null;
    }
    
    static <T extends Serializable> void saveData(T data, String dataPath) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> {            
            createIfNotExists(dataPath);

            try (FileOutputStream fileOut = new FileOutputStream(dataPath); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(data);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataInDisk.class.getName()).log(Level.WARNING, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DataInDisk.class.getName()).log(Level.WARNING, null, ex);
            } finally {
                executor.shutdown();
            }
        });
    }
    
    static boolean createIfNotExists(String path) {        
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
    
    static String getRelativePath(String path) {
        return new File(System.getProperty("user.dir"), path).getPath();
    }
    
    static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return null;
    }
    public static void saveDict(Dictionary dict){
        StringBuilder sb = new StringBuilder();
        dict.forEach((k,v)->{
            sb.append(k).append(":").append(v).append("\n");
        });
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getRelativePath(PATH_DICT)), "UTF-8"))) {
            out.write(sb.toString());
        } catch (Exception ex) {
        }
    }
    public static void addWordToDict(String key, String value){
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getRelativePath(PATH_DICT), true), "UTF-8"))) {
            out.write(key + ":" + value + "\n");
        } catch (Exception ex) {
        }
    }
        
    public static Dictionary loadDict(){
        Dictionary dict = new Dictionary();
        try (FileInputStream fis = new FileInputStream(getRelativePath(PATH_DICT))) {
            
            Scanner scanner = new Scanner(fis);
            while(scanner.hasNextLine()){
                String[] data = scanner.nextLine().split(":",2);
                dict.put(data[0], data[1]);
            }
            return dict;
        } catch (Exception ex) {
        }
        return dict;       
    } 
}
