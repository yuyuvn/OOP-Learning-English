/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.Article;
import englishlearning.model.ArticleBuilder;
import englishlearning.model.Articles;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Clicia
 */
public class DataInNetTest {
    
    public DataInNetTest() {
    }

    /**
     * Test of getListArticle method, of class DataInNet.
     */
    /*@Test
    public void testGetListArticle_String() throws Exception {
    System.out.println("getListArticle");
    String urlString = "";
    Articles expResult = null;
    Articles result = DataInNet.getListArticle(urlString);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }*/

    /**
     * Test of getListArticle method, of class DataInNet.
     */
    /*@Test
    public void testGetListArticle_0args() throws Exception {
    System.out.println("getListArticle");
    Articles expResult = null;
    Articles result = DataInNet.getListArticle();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
    }*/

    /**
     * Test of getArticle method, of class DataInNet.
     */
    @Test
    public void testGetArticle() throws Exception {
        System.out.println("getArticle");
        //Article article = DataInNet.getListArticle().get(0);
        Article article = ArticleBuilder.create().guid("http://learningenglish.voanews.com/content/virginia-textbooks-sea-of-japan-east-sea/1903561.html").build();
        System.out.println("Content of "+ article.getLink());
        //Article expResult = null;
        Article result = DataInNet.getArticle(article);
        System.out.println(result.getContent());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
