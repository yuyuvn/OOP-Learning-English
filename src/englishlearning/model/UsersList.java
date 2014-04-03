/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.HashMap;

/**
 *
 * @author Clicia
 * @param <K> username
 * @param <V>
 */
public class UsersList<K extends String,V> extends HashMap<K,V> {
    private K username;
        
    public UsersList() {
        super();
    }
    
    public UsersList(String datapath) {
        super();
        readData(datapath);
    }
    
    public String getUsername() {
        return username;
    }
    
    public final void readData(String datapath) {
        // TODO: read data from file
    }
    
    public void setUsername(K name) {
        username = name;
        this.put(name, null);
    }
    
    public final void saveData(String datapath) {
        // TODO: write data to file
    }
}
