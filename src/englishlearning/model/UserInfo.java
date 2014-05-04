/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Clicia
 */
public class UserInfo implements Serializable {
    private String name;
    private String lastUsed;
    private Map<String,Double> readList;
    private PlayState playState;
    
    // add favorite (thêm nếu có thời gian)
    // public List<String> favorite;
    
    //<editor-fold defaultstate="collapsed" desc="Must use builder">
    UserInfo() {
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setter & getter">
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastUsed() {
        return lastUsed;
    }
    
    public void setLastUsed(String lastUsed) {
        this.lastUsed = lastUsed;
    }
    
    public Map<String, Double> getReadList() {
        return readList;
    }
    
    public void setReadList(Map<String, Double> readList) {
        this.readList = readList;
    }
    
    public PlayState getPlayState() {
        return playState;
    }
    
    public void setPlayState(PlayState playState) {
        this.playState = playState;
    }
//</editor-fold>
        
}
