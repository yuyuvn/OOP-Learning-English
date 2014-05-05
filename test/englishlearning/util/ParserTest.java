/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Clicia
 */
public class ParserTest {
    
    public ParserTest() {
    }

    /**
     * Test of convert2link method, of class Parser.
     */
    @Test
    public void testConvert2link() {
        System.out.println("convert2link");
        String input = "I am Clicia Scarlet";
        String expResult = "I am [Clicia] [Scarlet]";
        String result = Parser.convert2link(input);
        assertEquals(expResult, result);
    }
    
}
