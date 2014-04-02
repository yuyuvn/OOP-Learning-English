/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.views.ILoginView;
import java.awt.MouseInfo;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import org.controlsfx.control.PopOver;

/**
 *
 * @author Clicia
 * @param <V>
 * @param <M>
 */
public class LoginPresenter<V extends ILoginView, M> extends Presenter<V,M> {    
    @Override
    public void setView(V view) {
        super.setView(view);
        
        this.getView().getButton().setOnAction((ActionEvent t) -> {
            getView().getLabel().setText("[Hello] [World] is [blah] [blah] abc []xyz [blah] [blah] [blah] [blah]!");
        });
        getView().getLabel().setOnAction((ActionEvent event) -> {
            Hyperlink link = (Hyperlink)event.getSource();
            final String str = link == null ? "" : link.getText();
            double targetX = MouseInfo.getPointerInfo().getLocation().x;
            double targetY = MouseInfo.getPointerInfo().getLocation().y;
            
            
            if (getView().getPopOver().isDetached()) {
                getView().setPopOver(new PopOver());
            }
            
            getView().getPopOver().show(link, targetX, targetY);
        });
        
    }
}
