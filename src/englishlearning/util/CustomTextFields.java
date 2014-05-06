/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.util;

import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.textfield.CustomTextField;

/**
 *
 * @author Clicia
 */
public class CustomTextFields {
    private static final Duration FADE_DURATION = Duration.millis(350);
    
    public static boolean getClearButtonField(CustomTextField inputField) {
        return true;
    }
    public static void setClearButtonField(CustomTextField searchField, boolean option) {
        if (!option) return;
        searchField.getStyleClass().add("search-field"); //$NON-NLS-1$

        Region clearButton = new Region();
        clearButton.getStyleClass().addAll("graphic"); //$NON-NLS-1$
        StackPane clearButtonPane = new StackPane(clearButton);
        clearButtonPane.getStyleClass().addAll("clear-button"); //$NON-NLS-1$
        clearButtonPane.setOpacity(0.0);
        clearButtonPane.setCursor(Cursor.DEFAULT);

        clearButtonPane.setOnMouseReleased((MouseEvent e) -> {
            searchField.clear();
        });

        searchField.setRight(clearButtonPane);

        final FadeTransition fader = new FadeTransition(FADE_DURATION, clearButtonPane);
        fader.setCycleCount(1);

        searchField.textProperty().addListener(new InvalidationListener() {

            @Override public void invalidated(Observable arg0) {
                String text = searchField.getText();
                boolean isTextEmpty = text == null || text.isEmpty();
                boolean isButtonVisible = fader.getNode().getOpacity() > 0;

                if (isTextEmpty && isButtonVisible) {
                    setButtonVisible(false);
                } else if (!isTextEmpty && !isButtonVisible) {
                    setButtonVisible(true);
                }
            }

            private void setButtonVisible( boolean visible ) {
                fader.setFromValue(visible? 0.0: 1.0);
                fader.setToValue(visible? 1.0: 0.0);
                fader.play();
            }
        });
    }
}
