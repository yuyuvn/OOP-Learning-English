/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishlearning.viewscontroller.control;

import englishlearning.presenter.TitlePresenter;

/**
 *
 * @author Clicia
 */
public class TitleView extends englishlearning.viewscontroller.TitleView {
    final TitlePresenter presenter;
    public TitleView() {
        super();
        presenter = new TitlePresenter();
        this.setPresenter();
    }
    
    public final void setPresenter() {        
        presenter.setView(this);
    }
}
