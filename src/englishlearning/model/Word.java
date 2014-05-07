/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Clicia
 */
public class Word implements Serializable {
    private String word;
    private String mean;
    private Set<String> options;
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
    
    public Set<String> getOptions() {
        return options;
    }
    
    public void setOptions(Set<String> options) {
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
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.word);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Word{" + "word=" + word + ", mean=" + mean + ", options=" + options + ", answer=" + answer + ", choiced=" + choiced + '}';
    }
    
    
}
