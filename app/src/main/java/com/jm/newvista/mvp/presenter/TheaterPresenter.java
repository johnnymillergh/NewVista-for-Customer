package com.jm.newvista.mvp.presenter;

import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TheaterModel;
import com.jm.newvista.mvp.view.THeaterView;

/**
 * Created by Johnny on 3/24/2018.
 */

public class TheaterPresenter extends BasePresenter<TheaterModel, THeaterView> {
    private TheaterModel theaterModel;
    private THeaterView theaterView;

    public TheaterPresenter() {
        theaterModel = new TheaterModel();
        super.BasePresenter(theaterModel);
    }

    public void updateView() {
        theaterView = getView();
        theaterView.onUpdateView();
    }
}
