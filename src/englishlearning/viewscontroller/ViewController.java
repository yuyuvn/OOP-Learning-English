/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.viewscontroller;

import englishlearning.presenter.Presenter;

/**
 *
 * @author Clicia
 */
public abstract class ViewController extends View { 
    private final Presenter presenter;
 
    public ViewController() {
        super();
        this.presenter = null;
    }
    
    public ViewController(Presenter presenter) {
        super();
        this.presenter = presenter;
        this.initialisePresenter();
    }
 
    private void initialisePresenter() {
        if (this.presenter == null){
            throw new IllegalStateException();
        }
 
        this.presenter.setView(this);
    }
}
