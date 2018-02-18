package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.NewMovieReleasesModel;
import com.jm.newvista.mvp.view.NewMovieReleasesView;
import com.jm.newvista.util.ImageUtil;

import java.util.List;

/**
 * Created by Johnny on 2/6/2018.
 */

public class NewMovieReleasesPresenter extends BasePresenter<NewMovieReleasesModel, NewMovieReleasesView> {
    NewMovieReleasesModel newMovieReleasesModel;
    NewMovieReleasesView newMovieReleasesView;

    public NewMovieReleasesPresenter() {
        newMovieReleasesModel = new NewMovieReleasesModel();
        super.BasePresenter(newMovieReleasesModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void getNewMovie() {
        newMovieReleasesView = getView();
        newMovieReleasesModel.getAndSaveNewMoviePoster(new NewMovieReleasesModel
                .NewMovieReleasesCallbackListener() {
            @Override
            public void onFinishSavingPoster() {
                new AsyncTask<Void, Void, List>() {
                    @Override
                    protected List doInBackground(Void... voids) {
                        List<MovieEntity> newMovies = newMovieReleasesModel.getNewMovie();
                        return newMovies;
                    }

                    @Override
                    protected void onPostExecute(List list) {
                        newMovieReleasesView.onFinishPreparingNewMovie(list);
                    }
                }.execute();
            }
        });
    }
}
