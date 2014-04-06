/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.viewscontroller;

import englishlearning.presenter.MainWindowsPresenter;
import englishlearning.presenter.Presenter;
import englishlearning.views.IMainWindowsView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Clicia
 * @param <P>
 */
public class MainWindowView <P extends Presenter> extends ViewController<P> implements IMainWindowsView {
    @FXML 
    private Button exitButton;
    @FXML
    private Pane contains;
    @FXML
    private GridPane rootPane;
    @FXML
    private Label status1;
    @FXML
    private Label status2;
    
    public MainWindowView(P presenter) {
        super(presenter);
    }

    @Override
    public void setContains(View c) {
        contains.getChildren().clear();
        contains.getChildren().add(c);
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }

    @Override
    public void setStatus(int i, String text) {
        switch (i) {
            case 0:
                status1.setText(text);
                break;
            case 1:
                status2.setText(text);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
