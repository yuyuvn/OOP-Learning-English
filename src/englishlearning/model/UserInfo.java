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
    public String name;
    public Date lastUsed;
    public Map<String,Double> readList;
    public PlayState playState;
    
    // add favorite (thêm nếu có thời gian)
    // public List<String> favorite;
    
    //<editor-fold defaultstate="collapsed" desc="Must use builder">
    UserInfo() {
    }
//</editor-fold>
}
