package com.jm.newvista.mvp.presenter;

import android.util.Log;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MovieModel;
import com.jm.newvista.mvp.view.MovieView;

/**
 * Created by Johnny on 2/5/2018.
 */

public class MoviePresenter extends BasePresenter<MovieModel, MovieView> {
    MovieModel movieModel;
    MovieView movieView;

    public MoviePresenter() {
        movieModel = new MovieModel();
        super.BasePresenter(movieModel);
    }

    public void getMovie() {
        movieView = getView();
        movieModel.getAndSaveMovie(new MovieModel.MovieModelCallback() {
            @Override
            public void onSaveMovieFinish() {
                Log.v("getAndSaveMovie", getClass() + ", movie saved");
                movieView.onNotifyMovieSaved();
            }
        });
    }
}
