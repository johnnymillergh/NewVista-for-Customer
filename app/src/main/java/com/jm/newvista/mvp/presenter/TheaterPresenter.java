package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TheaterModel;
import com.jm.newvista.mvp.view.TheaterView;

/**
 * Created by Johnny on 3/24/2018.
 */

public class TheaterPresenter extends BasePresenter<TheaterModel, TheaterView> {
    private TheaterModel theaterModel;
    private TheaterView theaterView;

    public TheaterPresenter() {
        theaterModel = new TheaterModel();
        super.BasePresenter(theaterModel);
    }

    public void updateView() {
        theaterView = getView();
        theaterView.onUpdateView();
    }
}
