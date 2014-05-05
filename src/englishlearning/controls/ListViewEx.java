/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controls;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Clicia
 */
public class ListViewEx extends ListView {
    private BooleanProperty selected;

    public void fireSelected() {
        BooleanProperty property = selectedProperty();
        property.set(!property.get());
    }

    public BooleanProperty selectedProperty() {
        if (selected == null) selected = new SimpleBooleanProperty(this, "selected", false);
        return selected;
    }
    

    public ListViewEx() {
    }

    public ListViewEx(ObservableList items) {
        super(items);
    }
    
    
}
