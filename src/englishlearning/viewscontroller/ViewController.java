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
 * @param <P>
 */
public abstract class ViewController<P extends Presenter>
    extends UserControl {
 
    private final P presenter;
 
    public ViewController(P presenter) {
        this.presenter = presenter;
        this.initialisePresenter();
    }
 
    private void initialisePresenter() {
        if (this.presenter == null){
            throw new IllegalStateException();
        }
 
        this.presenter.setView(this);
    }
 
    protected P getPresenter() {
        return this.presenter;
    }
}
