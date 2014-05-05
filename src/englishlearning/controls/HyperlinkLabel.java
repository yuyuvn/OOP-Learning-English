/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controls;

import com.sun.javafx.event.EventHandlerManager;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Clicia
 */
public class HyperlinkLabel extends TextFlow {
    /***************************************************************************
     * 
     * Static fields
     * 
     **************************************************************************/
    
    // The strings used to delimit the hyperlinks
    private static final String HYPERLINK_START = "["; //$NON-NLS-1$
    private static final String HYPERLINK_END = "]"; //$NON-NLS-1$
    
    /***************************************************************************
     * 
     * Private fields
     * 
     **************************************************************************/
    
    private final EventHandler<ActionEvent> eventHandler = (final ActionEvent event) -> {
        EventHandler<ActionEvent> onActionHandler = getOnAction();
        if (onActionHandler != null) {
            onActionHandler.handle(event);
        }
    };
    private final EventHandlerManager eventHandlerManager =
            new EventHandlerManager(this);
    
    //<editor-fold defaultstate="collapsed" desc="Property text">
    private StringProperty text;
    
    public final String getText() {
        return textProperty().get();
    }
    
    public final void setText(String value) {
        textProperty().set(value);
    }
    
    public final StringProperty textProperty() {
        if (text == null) text = new SimpleStringProperty(this,"text", "");
        return text;
    }

//</editor-fold>
    
    public HyperlinkLabel() {
        textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            updateText();
        });
    }
    
    private void updateText() {
        final String textData = getText();
        
        if (textData == null || textData.isEmpty()) {
            this.getChildren().clear();
            return;
        }
        
        // parse the text and put it into an array list
        final List<Node> nodes = new ArrayList<>();
        
        int start = 0;
        final int textLength = textData.length();
        while (start != -1 && start < textLength) {
            int startPos = textData.indexOf(HYPERLINK_START, start);
            int endPos = textData.indexOf(HYPERLINK_END, startPos);
            
            // if the startPos is -1, there are no more hyperlinks...
            if (startPos == -1 || endPos == -1) {
                if (textLength > start) {
                    // ...but there is still text to turn into one last label
                    Label label = new Label(textData.substring(start));
                    nodes.add(label);
                    break;
                }
            }
            
            // firstly, create a label from start to startPos
            Text label = new Text(textData.substring(start, startPos));
            nodes.add(label);
            
            // if endPos is greater than startPos, create a hyperlink
            Hyperlink hyperlink = new Hyperlink(textData.substring(startPos + 1, endPos));
            hyperlink.setPadding(new Insets(0, 0, 0, 0));
            hyperlink.setOnAction(eventHandler);
            nodes.add(hyperlink);
            
            start = endPos + 1;
        }
        
        getChildren().addAll(nodes);
    }
    
    // --- onAction
    /**
     * The action, which is invoked whenever a hyperlink is fired. This
     * may be due to the user clicking on the hyperlink with the mouse, or by
     * a touch event, or by a key press.
     * @return 
     */
    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() {
        if (onAction == null) {
            onAction = new SimpleObjectProperty<EventHandler<ActionEvent>>(this, "onAction") { //$NON-NLS-1$
                @Override protected void invalidated() {
                    eventHandlerManager.setEventHandler(ActionEvent.ACTION, get());
                }
            };
        }
        return onAction;
    }
    private ObjectProperty<EventHandler<ActionEvent>> onAction;

    public final void setOnAction(EventHandler<ActionEvent> value) {
        onActionProperty().set( value);
    }

    public final EventHandler<ActionEvent> getOnAction() {
        return onAction == null ? null : onAction.get();
    }
}
