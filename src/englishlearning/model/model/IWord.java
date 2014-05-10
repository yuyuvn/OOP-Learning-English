/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.Options;
import englishlearning.model.Word;

/**
 *
 * @author PC
 */
public interface IWord extends IWrapper {
    Word getWord();
    
    boolean isRightAnswer();
    boolean isAnswered();
    //getWord setWord conflict
    String getMean();
    void setMean(String mean);
    Options getOptions();
    void setOptions(Options options);
    String getChose();
    void SetChose(String chose);
}
