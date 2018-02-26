package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MovieScheduleModel;
import com.jm.newvista.mvp.view.MovieScheduleView;

/**
 * Created by Johnny on 2/26/2018.
 */

public class MovieSchedulePresenter extends BasePresenter<MovieScheduleModel, MovieScheduleView> {
    private MovieScheduleModel movieScheduleModel;
    private MovieScheduleView movieScheduleView;

    public MovieSchedulePresenter() {
        movieScheduleModel = new MovieScheduleModel();
        super.BasePresenter(movieScheduleModel);
    }
}
