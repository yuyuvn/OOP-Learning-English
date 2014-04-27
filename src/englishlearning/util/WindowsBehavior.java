/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Clicia
 */
public class WindowsBehavior {
    private static Double initialX;
    private static Double initialY;
    
    public static void setDragDrop(final Pane p, final Stage s) {        
        p.setOnMousePressed((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY && !s.isMaximized()) {                
                initialX = me.getSceneX();
                initialY = me.getSceneY();
            }
        });
        p.setOnMouseDragged((MouseEvent me) -> {
            if (me.getButton() == MouseButton.PRIMARY && !s.isMaximized()) {
                s.getScene().getWindow().setX(me.getScreenX() - initialX);
                s.getScene().getWindow().setY(me.getScreenY() - initialY);
            }
        });
    }
    
    public static void setWindowSize(Window window, Double width, Double height) {
        Double oldWidth = window.getWidth();
        Double oldHeight = window.getHeight();
        
        window.setWidth(width);
        window.setHeight(height);
        
        window.centerOnScreen();
    }
}
