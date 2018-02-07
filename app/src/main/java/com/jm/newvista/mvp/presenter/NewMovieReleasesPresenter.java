package com.jm.newvista.mvp.presenter;

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

    public void getNewMovie() {
        newMovieReleasesView = getView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                newMovieReleasesModel.getAndSaveNewMoviePoster();
                List<MovieEntity> newMovies = newMovieReleasesModel.getNewMovie();
                for (int i = 0; i < newMovies.size(); i++) {
                    String encodedPosterStr = newMovies.get(i).getPosterStr();
                    newMovies.get(i).setPoster(ImageUtil.decode(encodedPosterStr));
                }
                newMovieReleasesView.onFinishPreparingNewMovie(newMovies);
            }
        }).start();
    }
}
