/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.views;

import englishlearning.presenter.Presenter;
import englishlearning.views.Controller.FXMLPath;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.stage.Stage;

/**
 *
 * @author Clicia
 */
@FXMLPath("Window.fxml")
public class MainWindow extends Window {
    //<editor-fold defaultstate="collapsed" desc="Property mainContent">
    private ReadOnlyObjectWrapper<MainContent> mainContent;
    
    public final MainContent getMainContent() {
        return mainContentProperty().get();
    }
    
    public final ReadOnlyObjectWrapper<MainContent> mainContentProperty() {
        if (mainContent == null) {
            mainContent = new ReadOnlyObjectWrapper<>(this, "mainContent", new MainContent());
        }
        return mainContent;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Property exercise">
    private ReadOnlyObjectWrapper<Exercise> exercise;
    
    public final Exercise getExercise() {
        return exerciseProperty().get();
    }
    
    public final ReadOnlyObjectWrapper<Exercise> exerciseProperty() {
        if (exercise == null) {
            exercise = new ReadOnlyObjectWrapper<>(this, "exercise", new Exercise());
        }
        return exercise;
    }
//</editor-fold>
    
    public MainWindow(Stage stage, Presenter presenter) {
        super(stage, presenter);
        
        setMinWidth(900);
        setMinHeight(600);        
        
        this.setTitle("VOA Learning English");
        this.showWindow();
    }
    
}
