/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.wrapper;

import englishlearning.model.Question;
import englishlearning.model.model.IQuestion;

/**
 *
 * @author PC
 * @param <T>
 */
public class QuestionWrapper<T extends Question> extends Wrapper<T> implements IQuestion{

    public QuestionWrapper(T data) {
        super(data);
    }
    
}
