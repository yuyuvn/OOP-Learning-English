/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.UserInfo;
import englishlearning.model.Word;
import englishlearning.model.WordBuilder;
import static englishlearning.util.DataInDisk.getUserInfo;
import java.net.MalformedURLException;
import org.junit.Test;

/**
 *
 * @author Clicia
 */
public class TestClass {
    @Test
    public void testMethod() throws Exception {
        UserInfo clicia = getUserInfo("Clicia");
        System.out.println(clicia);
        //clicia.getReadList().putIfAbsent("test", 0.0);
        System.out.println(clicia);
        Lookup.getDictionary().forEach((k,v)->{});
    }
}
