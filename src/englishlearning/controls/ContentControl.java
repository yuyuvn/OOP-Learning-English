/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Clicia
 */
public class ContentControl extends AnchorPane {
    //<editor-fold defaultstate="collapsed" desc="Property data">
    private ObjectProperty data;
    public final Object getData() { return dataProperty().get(); }
    public final void setData(Object value) { dataProperty().set(value); }
    public final ObjectProperty dataProperty() { 
        if (data == null) {
            data = new SimpleObjectProperty(this, "data");
            data.addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
                if (newValue instanceof javafx.scene.Node) {
                    this.getChildren().clear();
                    this.getChildren().add((Node) newValue);
                } else {
                    this.getChildren().clear();
                    this.getChildren().addAll(resources.get().stream()
                        .filter(d -> d.getDataClass().equals(newValue.getClass()))
                        .collect(Collectors.toList()));
                    this.getChildren().forEach(n -> setArcho(n));
                }
            });
        }
        
        return data; 
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Property resources">
    private ObjectProperty<List<DataTemplate>> resources;
    public final List<DataTemplate> getResources() { return resourcesProperty().get(); }
    public final void addResources(DataTemplate value) { getResources().add(value); }
    public final void clearResources(DataTemplate value) { getResources().clear(); }
    public final ObjectProperty<List<DataTemplate>> resourcesProperty() { 
        if (resources == null) resources = new SimpleObjectProperty<>(this, "resources", new ArrayList<>());
        return resources; 
    }
//</editor-fold>
    
    private void setArcho(Node n) {
        AnchorPane.setBottomAnchor(n, .0);
        AnchorPane.setLeftAnchor(n, .0);
        AnchorPane.setRightAnchor(n, .0);
        AnchorPane.setTopAnchor(n, .0);
    }
}
