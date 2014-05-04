/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author Clicia
 */
public class ContentControl extends Pane {
    private ObjectProperty data;
    public final Object getData() { return dataProperty().get(); }
    public final void setData(Object value) { dataProperty().set(value); }
    public final ObjectProperty dataProperty() { 
        if (data == null) {
            data = new SimpleObjectProperty(this, "data");
            data.addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
                if (newValue == null) {
                    this.getChildren().clear();
                    return;
                }
                Collection<Node> nodes = getResources().stream()
                            .filter(d -> d.getDataClass().isInstance(newValue))
                            .flatMap(d -> d.getChildren().stream())
                            .collect(Collectors.toList());
                if (newValue instanceof javafx.scene.Node && nodes.isEmpty()) {
                    this.getChildren().clear();
                    this.getChildren().add((Node) newValue);
                } else {
                    if (oldValue == null || !newValue.getClass().equals(oldValue.getClass())) {
                        this.getChildren().clear();
                        nodes.stream().forEach((node -> {
                            node.maxWidth(getWidth());
                            node.maxHeight(getHeight());
                        }));
                        this.getChildren().addAll(nodes);
                    }
                }
            });
        }
        
        return data; 
    }
    
    private ObjectProperty<List<DataTemplate>> resources;
    public final List<DataTemplate> getResources() { return resourcesProperty().get(); }
    public final void addResources(DataTemplate value) { getResources().add(value); }
    public final void clearResources(DataTemplate value) { getResources().clear(); }
    public final ObjectProperty<List<DataTemplate>> resourcesProperty() { 
        if (resources == null) resources = new SimpleObjectProperty<>(this, "resources", new ArrayList<>());
        return resources; 
    }
    
    @Override
    protected void layoutChildren() {
        getChildren().stream().forEach((node) -> {
            layoutInArea(node, 0, 0, getWidth(), getHeight(), 0, HPos.LEFT, VPos.TOP);
        });
    }
}
