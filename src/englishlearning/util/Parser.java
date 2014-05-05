/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Clicia
 */
public class Parser {
    private final static List<String> exclude = new ArrayList<>(Arrays.asList("and", "an", "the", "be", "has", "was", "will",
            "but", "a", "in", "from", "as", "on", "of", "for",
            "is", "at", "to", "out", "by", "voa", "us", "s",
            "not", "no", "yes", "it", "that", "had", "been",
            "off", "ap", "afp", "reuters", "he", "she", "it",
            "its", "are", "or", "go", "this", "we", "am", "me"));

    private static String __buffer;
    public static String convert2link(String input) {
        __buffer = input.replaceAll("[a-zA-Z][a-zA-Z]+", "[$0]");
        exclude.stream().forEach(s -> __buffer = __buffer.replaceAll("(?i)\\[" + s + "\\]", s));
        return __buffer;
    }
}
