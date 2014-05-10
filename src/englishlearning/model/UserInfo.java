/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;

/**
 *
 * @author Clicia
 */
public class UserInfo implements Serializable {
    private String name;
    private String lastUsed;
    private ReadList readList;
    private PlayState playState;
    private Integer point;
    
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
    
    public ReadList getReadList() {
        return readList;
    }
    
    public void setReadList(ReadList readList) {
        this.readList = readList;
    }
    
    public PlayState getPlayState() {
        return playState;
    }

    public void setPlayState(PlayState playState) {
        this.playState = playState;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
    
    

//</editor-fold>
    
    @Override
    public String toString() {
        return "UserInfo{" + "name=" + name + ", lastUsed=" + lastUsed + ", readList=" + readList + ", playState=" + playState + '}';
    }
            
}
