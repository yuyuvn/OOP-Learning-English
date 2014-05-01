/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.controls;

import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.util.Duration;

/**
 *
 * @author Clicia
 */
public class Loading extends HBox {
    //<editor-fold defaultstate="collapsed" desc="Property numberBlock">
    private IntegerProperty numberBlock;
    public final Integer getNumberBlock() { return numberBlockProperty().get(); }
    public final void setNumberBlock(Integer value) {
        numberBlockProperty().set(value);
        createRec();
    }
    public final IntegerProperty numberBlockProperty() {
        if (numberBlock == null) numberBlock = new SimpleIntegerProperty(this, "numberBlock", 6);
        return numberBlock;
    }
//</editor-fold>    
    //<editor-fold defaultstate="collapsed" desc="Property duration">
    private DoubleProperty duration;
    public final Double getDuration() { return durationProperty().get(); }
    public final void setDuration(Double value) {
        ft.setDuration(Duration.millis(value));
        durationProperty().set(value);
        createRec();
    }
    public final DoubleProperty durationProperty() {
        if (duration == null) duration = new SimpleDoubleProperty(this, "duration", 250.0);
        return duration;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property recSize">
    private DoubleProperty recSize;
    public double getRecSize() { return recSizeProperty().get(); }
    public void setRecSize(double value) { recSizeProperty().set(value); createRec();}
    public DoubleProperty recSizeProperty() {
        if (recSize == null) recSize = new SimpleDoubleProperty(this, "recSize", 10.0);
        return recSize;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property color">
    private ObjectProperty<Paint> color;
    public Paint getColor() { return (Paint) colorProperty().get(); }
    public void setColor(Paint value) { colorProperty().set(value); createRec();}
    public ObjectProperty colorProperty() {
        if (color == null) color = new SimpleObjectProperty<>(this, "color", Color.BLUE);
        return color;
    }
//</editor-fold>

    private final FadeTransition ft = new FadeTransition();
    private int target = 0;
    
    public Loading() {
        createContent();
    }

    public Loading(double spacing) {
        super(spacing);
        createContent();
    }
    
    private void createContent() {
        createRec();
        setTransition();
    }
    
    private void setTransition() {
        ft.setDuration(Duration.millis(getDuration()));
        setTarget(ft);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.setOnFinished((ActionEvent event) -> {
            FadeTransition f = (FadeTransition) event.getSource();
            setTarget(f);
            f.play();
        });

        ft.play();
    }
    
    private void createRec() {
        if (ft != null) ft.stop();
        getChildren().clear();
        for (int i = 0; i < getNumberBlock(); i++) {
            Rectangle r = RectangleBuilder.create()
                    .width(getRecSize())
                    .height(getRecSize())
                    .fill(getColor())
                    .opacity(0.0)
                    .styleClass("loading-block")
                    .build();
            getChildren().add(r);
        }
        if (ft != null) setTarget(ft);
        if (ft != null) ft.play();
    }
    
    private void setTarget(FadeTransition f) {
        target = (target + 1) % getNumberBlock();
        f.setNode(this.getChildren().get(target));
    }
    
}
