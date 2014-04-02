/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.viewscontroller;

import englishlearning.presenter.MainWindowsPresenter;
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
public class MainWindowView <P extends MainWindowsPresenter> extends ViewController<P> implements IMainWindowsView {
    @FXML
    private Button exitButton;
    @FXML
    private View contain;
    @FXML
    private GridPane rootPane;
    
    public MainWindowView(P presenter) {
        super(presenter);
    }

    @Override
    public Button getExitButton() {
        return this.exitButton;
    }

    @Override
    public void setContains(View c) {
        if (this.contain == null) {
            GridPane.setRowIndex(c, 1);
            rootPane.getChildren().add(c);
        }
        this.contain = c;
    }

    @Override
    public Label getStatusLabel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pane getRootPane() {
        return rootPane;
    }
}
