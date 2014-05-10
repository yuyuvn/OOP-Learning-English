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
        if (value < 1) throw new IllegalArgumentException("Number block must larger than 0");
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
        if (value < 1) throw new IllegalArgumentException("Duration must larger than 0");
        __ft.setDuration(Duration.millis(value));
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
    public void setRecSize(double value) { 
        if (value < 1) throw new IllegalArgumentException("Block size must larger than 0");
        recSizeProperty().set(value);
        createRec();
    }
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

    private final FadeTransition __ft = new FadeTransition();
    private int __target;
    
    public Loading() {
        init();
    }

    public Loading(double spacing) {
        super(spacing);
        init();
    }
    
    private void createContent() {
        createRec();
        setTransition();
    }
    
    private void setTransition() {
        __ft.setDuration(Duration.millis(getDuration()));
        setTarget(__ft);
        __ft.setFromValue(0.0);
        __ft.setToValue(1.0);
        __ft.setCycleCount(2);
        __ft.setAutoReverse(true);
        __ft.setOnFinished((ActionEvent event) -> {
            FadeTransition f = (FadeTransition) event.getSource();
            setTarget(f);
            f.play();
        });

        __ft.play();
    }
    
    private void init() {
        this.getStyleClass().add("loading");
        createContent();
    }
    
    private void createRec() {
        if (__ft != null) __ft.stop();
        getChildren().clear();
        __target = 0;
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
        if (__ft != null) setTarget(__ft);
        if (__ft != null) __ft.play();
    }
    
    private void setTarget(FadeTransition f) {
        __target = (__target + 1) % getNumberBlock();
        f.setNode(this.getChildren().get(__target));
    }
    
}
