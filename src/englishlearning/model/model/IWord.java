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
    public String getMean();
    public void setMean(String mean);
    public Options getOptions();
    public void setOptions(Options options);
    public int getAnswer();
    public void setAnswer(int answer);
    public int getChoiced();
    public void setChoiced(int choiced);
}
