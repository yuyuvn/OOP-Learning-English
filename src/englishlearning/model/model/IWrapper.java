/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.property.WrapperProperty;
import java.util.List;

/**
 *
 * @author Clicia
 */
public interface IWrapper {
    List<WrapperProperty> getProperties(); 
    void addProperty(WrapperProperty property);
    void removeProperty(WrapperProperty property);
    void fireValueChangedEvent();
}
