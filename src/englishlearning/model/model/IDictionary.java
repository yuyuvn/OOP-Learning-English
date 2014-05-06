/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.Dictionary;
import englishlearning.model.Word;

/**
 *
 * @author Clicia
 */
public interface IDictionary extends IWrapper {
    Dictionary<String,String> getDictionary();
    String get(String word);
    String get(Word word);
    void add(String word, String mean);
    void add(Word word);
    Word getWord(String word);
}
