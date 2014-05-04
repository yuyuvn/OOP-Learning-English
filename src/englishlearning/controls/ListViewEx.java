/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Clicia
 */
public class ListViewEx extends ListView {
    private ObjectProperty selectedItem;

    public Object getSelectedItem() {
        return selectedItemProperty().get();
    }

    public void setSelectedItem(Object value) {
        selectedItemProperty().set(value);
    }

    public ObjectProperty selectedItemProperty() {
        if (selectedItem == null) selectedItem = new SimpleObjectProperty(this, "selectedItem");
        return selectedItem;
    }

    public ListViewEx() {
    }

    public ListViewEx(ObservableList items) {
        super(items);
    }
    
    
}
