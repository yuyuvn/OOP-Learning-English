/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning;

import englishlearning.presenter.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 *
 * @author Clicia
 */
public class EnglishLearning extends Application {    
    @Override
    public void start(Stage stage) throws Exception {
        MainWindow mainWindow = new MainWindow();
        mainWindow.showWindows(stage);
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
