/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author Clicia
 */
public class UserInfo implements Serializable {
    public String name;
    public String lastUsed;
    public LinkedHashMap<String,Object> readList; // TODO: change object to model score
    // TODO: trạng thái chơi dở (có đang chơi hay không? để có thể resume)
    
    // add favorite (thêm nếu có thời gian)
    // public HashSet<String> favorite;
}
