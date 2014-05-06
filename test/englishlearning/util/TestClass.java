/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.Article;
import englishlearning.model.Articles;
import englishlearning.model.model.IArticle;
import englishlearning.model.wrapper.ArticleWrapper;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.*;
import org.junit.Test;
import sun.net.www.http.HttpClient;

/**
 *
 * @author Clicia
 */
public class TestClass {
    @Test
    public void testMethod() throws MalformedURLException {
        String str = "You can use a Matcher to find all matches to a regular repression";

	// find all words starting with m or c, and ends with n or r or s. 
	// RegEx backslash should be escaped with an additional one.
	Pattern p = Pattern.compile("\\w+ ");
	Matcher m = p.matcher(str);
	while (m.find()) { // find next match
	    String match = m.group();
	    System.out.println(match);
	}

        // false because regex does not match the whole string
        System.out.println("Matches: " + m.matches());
    }
}
