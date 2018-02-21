package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MovieModel;
import com.jm.newvista.mvp.view.MovieView;
import com.jm.newvista.util.ImageUtil;

/**
 * Created by Johnny on 2/11/2018.
 */

public class MoviePresenter extends BasePresenter<MovieModel, MovieView> {
    private MovieModel movieModel;
    private MovieEntity movieEntity;

    public MoviePresenter() {
        movieModel = new MovieModel();
        super.BasePresenter(movieModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void getAndDisplayMovie() {
        Intent intent = getView().onGetIntent();
        String from = intent.getStringExtra("from");
        int movieId;
        switch (from) {
            case "NewMovieReleases":
                movieId = intent.getIntExtra("movieId", 0);
                new AsyncTask<Integer, Void, MovieEntity>() {
                    @Override
                    protected MovieEntity doInBackground(Integer... integers) {
                        movieEntity = movieModel.getMovieFromDB(integers[0]);
                        return movieEntity;
                    }

                    @Override
                    protected void onPostExecute(MovieEntity movieEntity) {
                        getView().onUpdateMovieInformation(movieEntity);
                    }
                }.execute(movieId);
                break;
            case "SearchResult":
                movieId = intent.getIntExtra("movieId", 0);
                new AsyncTask<Integer, Void, MovieEntity>() {
                    @Override
                    protected MovieEntity doInBackground(Integer... integers) {
                        movieEntity = movieModel.getMovieFromDB(integers[0]);
                        return movieEntity;
                    }

                    @Override
                    protected void onPostExecute(MovieEntity movieEntity) {
                        getView().onUpdateMovieInformation(movieEntity);
                    }
                }.execute(movieId);
                break;
            case "TopMovie":
                final String title = intent.getStringExtra("title");
                new AsyncTask<Void, Void, MovieEntity>() {
                    @Override
                    protected MovieEntity doInBackground(Void... voids) {
                        movieEntity = movieModel.getMovieFromDB(title);
                        return movieEntity;
                    }

                    @Override
                    protected void onPostExecute(MovieEntity movieEntity) {
                        getView().onUpdateMovieInformation(movieEntity);
                    }
                }.execute();
                break;
            default:
        }
    }

    public void displayDescriptionDialog() {
        if (movieEntity == null) return;
        getView().onDisplayDescriptionDialog();
    }

    public void displayAllDetailsDialog() {
        if (movieEntity == null) return;
        getView().onDisplayAllDetailsDialog();
    }

    public String getMovieTitle() {
        return movieEntity.getTitle();
    }
}
