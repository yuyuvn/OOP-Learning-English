/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Clicia
 */
public class Word implements Serializable {
    private String text;
    private String mean;
    private Options options;
    private String chose;

    //<editor-fold defaultstate="collapsed" desc="Must use builder">
    public Word() {
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getter & setter">
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    public String getMean() {
        return mean;
    }
    
    public void setMean(String mean) {
        this.mean = mean;
    }
    
    public Options getOptions() {
        return options;
    }
    
    public void setOptions(Options options) {
        this.options = options;
    }

    public String getChose() {
        return chose;
    }

    public void setChose(String chose) {
        this.chose = chose;
    }
    
    

//</editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.text);
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
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Word{" + "text=" + text + ", mean=" + mean + ", options=" + options + ", chose=" + chose + '}';
    }
    
    
}
