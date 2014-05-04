/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 *
 * @author Clicia
 */
@DefaultProperty("resources")
public class DataTemplate extends Parent {

    private String dataType;
    public String getDataType() { return dataType; }
    public void setDataType(String className) {
        try {
            dataClass = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dataType = className;
    }
    
    private Class<?> dataClass;
    public Class<?> getDataClass() { return dataClass; }
    public void setDataClass(Class<?> dataClass) {
        this.dataClass = dataClass;
        this.dataType = dataClass.getCanonicalName();
    }
    
    private ObjectProperty<List<Node>> resources;
    public final List<Node> getResources() { return resourcesProperty().get(); }
    public final void addResources(Node value) { getResources().add(value); }
    public final void clearResources(Node value) { getResources().clear(); }
    public final ObjectProperty<List<Node>> resourcesProperty() { 
        if (resources == null) resources = new SimpleObjectProperty<>(this, "resources", new ArrayList<>());
        return resources; 
    }
}
