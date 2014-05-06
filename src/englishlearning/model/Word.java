/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.util.List;

/**
 *
 * @author Clicia
 */
public class Word {
    private String word;
    private String mean;
    private List<String> options;
    private int answer;
    private int choiced;

    //<editor-fold defaultstate="collapsed" desc="Must use builder">
    public Word() {
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter & setter">
    public void setWord(String word) {
        this.word = word;
    }
    
    public String getWord() {
        return word;
    }
    
    public String getMean() {
        return mean;
    }
    
    public void setMean(String mean) {
        this.mean = mean;
    }
    
    public List<String> getOptions() {
        return options;
    }
    
    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    public int getAnswer() {
        return answer;
    }
    
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    
    public int getChoiced() {
        return choiced;
    }
    
    public void setChoiced(int choiced) {
        this.choiced = choiced;
    }
//</editor-fold>
        
}
