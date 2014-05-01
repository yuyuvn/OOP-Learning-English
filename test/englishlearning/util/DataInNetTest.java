/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.Article;
import englishlearning.model.Articles;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Clicia
 */
public class DataInNetTest {
    
    public DataInNetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getListArticle method, of class DataInNet.
     */
    @Test
    public void testGetListArticle_String() throws Exception {
        System.out.println("getListArticle");
        String urlString = "http://learningenglish.voanews.com/api/epiqq";
        //Articles expResult = null;
        Articles result = DataInNet.getListArticle(urlString);
        result.stream().forEach(a -> System.out.println(a));
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getListArticle method, of class DataInNet.
     */
    @Test
    public void testGetListArticle() throws Exception {
        //System.out.println("getListArticle");
        //Articles expResult = null;
        //List<Article> result = DataInNet.getListArticle();
        //assertEquals(expResult, result);
        //result.stream().forEach(a -> System.out.println(a));
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
