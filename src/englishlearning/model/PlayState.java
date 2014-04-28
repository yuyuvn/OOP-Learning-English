/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 *
 * @author Clicia
 */
public class PlayState implements Serializable {
    public String articleGUID;
    
    public LinkedHashMap<String,Object> allQuestions; // TODO change object to question model
    public HashSet<String> questions; // question remain, for perfome
    public LinkedHashMap<String,Integer> answers;
}
