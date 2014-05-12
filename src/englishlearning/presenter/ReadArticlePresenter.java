/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.model.model.IArticle;
import englishlearning.model.model.IWord;
import englishlearning.model.property.ReadOnlyWrapperProperty;
import englishlearning.util.Lookup;
import englishlearning.views.ReadArticle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Clicia
 * @param <V>
 */
public class ReadArticlePresenter<V extends ReadArticle> extends Presenter<V> {    
    //<editor-fold defaultstate="collapsed" desc="Property selectedWord">    
    public final ReadOnlyWrapperProperty<IWord> selectedWordProperty() {
        return getView().selectedWordProperty();
    }
//</editor-fold>
    
    private ExecutorService executor;

    public ReadArticlePresenter() {
    }

    public ReadArticlePresenter(V view) {
        super(view);
    }
    
    @Override
    protected void initialize() {
        // user selected a word
        getView().selectedWordProperty().addListener((ObservableValue<? extends IWord> observable, IWord oldValue, IWord newValue) -> {
            if (newValue == oldValue) return;
            getView().showPopOver();
            EventHandler<MouseEvent> hd = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getView().hidePopOver();
                    Scene scene = (Scene)mouseEvent.getSource();
                    scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }
            };
            getView().getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, hd);
            
            
            if (executor != null && !executor.isTerminated()) {                
                executor.shutdownNow();
            }            
            executor = Executors.newCachedThreadPool();
            
            Task<String> task = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    return Lookup.get(newValue.getWord());
                }
            };

            task.valueProperty().addListener(t -> {
                getView().getSelectedWord().fireValueChangedEvent();
                executor.shutdown();
            });

            executor.submit(task); 
        });
    }
        
    public void setData(IArticle article) {
        getView().setArticle(article);
    }
    
    public IArticle getData() {
        return getView().getArticle();
    }
}
