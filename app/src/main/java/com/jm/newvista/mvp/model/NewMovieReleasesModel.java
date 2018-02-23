package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/7/2018.
 */

public class NewMovieReleasesModel extends BaseModel {
    MyOkHttp myOkHttp;
    List<MovieEntity> newMovieList = new ArrayList<>();
    int savedMovieCount = 0;

    public NewMovieReleasesModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
        newMovieList = getNewMovie();
    }

    public List<MovieEntity> getNewMovie() {
        MovieDao dao = new MovieDao();
        List<MovieEntity> entities = dao.getAll();
        int endIndex = entities.size();
        int startIndex = endIndex - 10;
        if (newMovieList.size() != 0) {
            newMovieList.clear();
        }
        // Reverse list item
        for (int i = endIndex - 1; i >= startIndex; i--) {
            newMovieList.add(entities.get(i));
        }
        return newMovieList;
    }

    public void getAndSaveNewMoviePoster(final NewMovieReleasesCallbackListener newMovieReleasesCallbackListener) {
        newMovieReleasesCallbackListener.onFinishSavingPoster();
    }

    private void updateNewMoviePoster(MovieEntity entity, NewMovieReleasesCallbackListener
            newMovieReleasesCallbackListener) {
        Log.v("updateNewMoviePoster", getClass() + ", movie poster saved");
        MovieDao dao = new MovieDao();
        dao.updatePosterStrByTitle(entity);
        savedMovieCount++;
        if (savedMovieCount == newMovieList.size()) {
            newMovieReleasesCallbackListener.onFinishSavingPoster();
        }
    }

    @Override
    public void cancel() {
        Log.v("cancel", getClass().toString());
        myOkHttp.cancel(this);
    }

    public interface NewMovieReleasesCallbackListener {
        void onFinishSavingPoster();
    }
}
