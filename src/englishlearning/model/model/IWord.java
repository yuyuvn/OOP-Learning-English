/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.Word;

/**
 *
 * @author PC
 */
public interface IWord extends IWrapper {
    Word getWord();
    
    boolean isRightAnswer();
    boolean isAnswered();
}
