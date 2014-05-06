/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.Dictionary;
import englishlearning.model.Word;
import englishlearning.model.WordBuilder;

/**
 *
 * @author Clicia
 */
public class Lookup {
    private static Dictionary<String,String> __dictionary;
    
    public static Dictionary<String,String> getDictionary() {
        // TODO: if __dictionary == null then read from disk
        if (__dictionary == null) __dictionary = new Dictionary<>();
        return __dictionary;
    }
    
    public static String get(String word) {
        String mean = getDictionary().get(word);
        if (mean == null) {
            mean = DataInNet.getMean(word);
            if (mean != null) add(word,mean);
        }
        return mean;
    }

    public static void add(String word, String mean) {
        getDictionary().put(word, mean);
        // TODO: save to disk
    }

    public static String get(Word word) {
        String mean = get(word.getWord());
        if (mean != null) word.setMean(mean);
        else mean = word.getMean();
        return mean;
    }

    public static void add(Word word) {
        add(word.getWord(),word.getMean());
    }

    public static Word getWord(String word) {
        String mean = get(word);
        return WordBuilder.create().word(word).mean(mean == null ? "" : mean).build();
    }
}
