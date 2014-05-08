/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import englishlearning.model.Dictionary;
import englishlearning.model.Options;
import englishlearning.model.Word;
import englishlearning.model.WordBuilder;
import java.util.Random;

/**
 *
 * @author Clicia
 */
public class Lookup {
    private static Dictionary __dictionary;
    
    public static Dictionary getDictionary() {
        // TODO: if __dictionary == null then read from disk
        if (__dictionary == null) {
            __dictionary = DataInDisk.loadDict();
        }
        return __dictionary;
    }
    
    public static String get(String word) {
        word = word.toLowerCase();
        String mean = getDictionary().get(word);
        if (mean == null) {
            mean = DataInNet.getMean(word);
            if (mean == null) {
                if (word.endsWith("s")) return get(word.substring(0, -1));
                else if (word.endsWith("ed")) return get(word.substring(0, -2));
            }
            if (mean != null) add(word,mean);
        }
        return mean;
    }

    public static void add(String word, String mean) {
        getDictionary().put(word, mean);
        DataInDisk.addWordToDict(word, mean);
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
    
    public static Word populateOption(Word word) {
        if (word.getMean() == null || word.getMean().equals("")) throw new IllegalArgumentException("Word doesn't have mean");
        Options options = word.getOptions();
        if (options.size() > 0) throw new IllegalArgumentException("Word has been populated");
        options.add(word.getMean());
        Random generator = new Random();
        int dicSize = getDictionary().size();
        if (dicSize >= 4) {
            String[] values = new String[dicSize];
            values = getDictionary().values().toArray(values);
            while (options.size() < 4) {
                options.add(values[generator.nextInt(values.length)]);
            }
        } else {
            throw new IllegalArgumentException("Dictionary is too small to test");
        }
        Object[] o = options.toArray();
        for (int i = 0; i < 4; i++) {
            if (o[i].equals(word.getMean())) word.setAnswer(i+1);
        }
        return word;
    }
}
