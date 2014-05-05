/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.model.model;

import englishlearning.model.property.WrapperProperty;

/**
 *
 * @author Clicia
 */
public interface IWrapper {
    WrapperProperty getProperty();
    void setProperty(WrapperProperty property);
    void setChanged(boolean __changed);
}
