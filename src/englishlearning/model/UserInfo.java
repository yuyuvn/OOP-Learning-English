/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Clicia
 */
public class UserInfo implements Serializable {
    public String name;
    public Date lastUsed;
    public ReadList readList;
    public PlayState playState;
    
    // add favorite (thêm nếu có thời gian)
    // public HashSet<String> favorite;
}
