package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MovieScheduleDetailModel;
import com.jm.newvista.mvp.view.MovieScheduleDetailView;

/**
 * Created by Johnny on 3/24/2018.
 */

public class MovieScheduleDetailPresenter extends BasePresenter<MovieScheduleDetailModel, MovieScheduleDetailView> {
    private MovieScheduleDetailModel movieScheduleDetailModel;
    private MovieScheduleDetailView movieScheduleDetailView;

    public MovieScheduleDetailPresenter() {
        movieScheduleDetailModel = new MovieScheduleDetailModel();
        super.BasePresenter(movieScheduleDetailModel);
    }
}
