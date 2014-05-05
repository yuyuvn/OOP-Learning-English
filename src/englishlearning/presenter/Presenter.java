/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.presenter;

import englishlearning.views.Controller;

/**
 *
 * @author Clicia
 * @param <V>
 */
public abstract class Presenter<V extends Controller> {
    private V view;
    
    public Presenter() {
        
    }
    
    public Presenter(V view) {
        setView(view);
    }
    
    public final void setView(V view) {
        if (this.view != null) throw new RuntimeException("View had been set");
        this.view = view;
        this.initialize();
    }
    
    protected V getView() {
        return view;
    }
    
    protected abstract void initialize();
}
