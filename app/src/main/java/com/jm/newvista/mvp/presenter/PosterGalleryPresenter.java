package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.mvp.model.PosterGalleryModel;
import com.jm.newvista.mvp.view.PosterGalleryView;
import com.jm.newvista.util.ImageUtil;

/**
 * Created by Johnny on 2/11/2018.
 */

public class PosterGalleryPresenter extends BasePresenter<PosterGalleryModel, PosterGalleryView> {
    PosterGalleryModel posterGalleryModel;

    public PosterGalleryPresenter() {
        posterGalleryModel = new PosterGalleryModel();
        super.BasePresenter(posterGalleryModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void getAndDisplayMovie() {
        Intent intent = getView().onGetIntent();
        int movieId = intent.getIntExtra("movieId", 0);
        new AsyncTask<Integer, Void, MovieEntity>() {
            @Override
            protected MovieEntity doInBackground(Integer... integers) {
                MovieDao movieDao = new MovieDao();
                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setId(integers[0]);
                movieEntity = movieDao.queryById(movieEntity);
                movieEntity.setPoster(ImageUtil.decode(movieEntity.getPosterStr()));
                return movieEntity;
            }

            @Override
            protected void onPostExecute(MovieEntity movieEntity) {
                getView().onUpdate(movieEntity);
            }
        }.execute(movieId);
    }
}
