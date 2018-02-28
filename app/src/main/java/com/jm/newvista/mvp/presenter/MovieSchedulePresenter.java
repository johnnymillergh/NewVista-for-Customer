package com.jm.newvista.mvp.presenter;

import android.content.Intent;

import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MovieScheduleModel;
import com.jm.newvista.mvp.view.MovieScheduleView;
import com.jm.newvista.ui.adapter.MovieScheduleRecyclerViewAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by Johnny on 2/26/2018.
 */

public class MovieSchedulePresenter extends BasePresenter<MovieScheduleModel, MovieScheduleView> {
    private MovieScheduleModel movieScheduleModel;
    private MovieScheduleView movieScheduleView;

    private String movieTitle;

    public MovieSchedulePresenter() {
        movieScheduleModel = new MovieScheduleModel();
        super.BasePresenter(movieScheduleModel);
    }

    public void updateTooBarTitle() {
        movieScheduleView = getView();
        Intent intent = movieScheduleView.onGetIntent();
        movieTitle = intent.getStringExtra("movieTitle");
        movieScheduleView.onSetToolBarTitle(movieTitle);
    }

    public void getAndDisplayMovieSchedule() {
        final MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();
        movieScheduleEntity.setMovieTitle(movieTitle);

        movieScheduleModel.getMovieScheduleFromServer(movieScheduleEntity, new MovieScheduleModel
                .GetMovieScheduleListener() {
            @Override
            public void onSuccess(List<MovieScheduleEntity> movieSchedules) {
                MovieScheduleRecyclerViewAdapter adapter = movieScheduleView.onGetMovieScheduleRecyclerViewAdapter();
                adapter.setMovieSchedules(sortByPriceInAscendingOrder(movieSchedules));
                adapter.notifyItemRangeChanged(0, movieSchedules.size());
            }

            @Override
            public void onNullResult() {
                movieScheduleView.onNullResult();
            }

            @Override
            public void onFailure(String errorMessage) {
                movieScheduleView.onGetMovieScheduleFailure();
            }
        });
    }

    private List<MovieScheduleEntity> sortByPriceInAscendingOrder(List<MovieScheduleEntity> movieSchedules) {
        Collections.sort(movieSchedules);
        return movieSchedules;
    }
}
