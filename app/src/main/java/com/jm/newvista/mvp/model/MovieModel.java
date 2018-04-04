package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/11/2018.
 */

public class MovieModel extends BaseModel {
    private MyOkHttp myOkHttp;

    public MovieModel() {
        myOkHttp = NetworkUtil.myOkHttp;
    }

    public MovieEntity getMovieFromDB(int id) {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(id);
        return movieDao.queryById(movieEntity);
    }

    public MovieEntity getMovieFromDB(String title) {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(title);
        return movieDao.queryMovieByTitle(movieEntity);
    }

    public void getLowestPriceByMovieTitle(String movieTitle, final GetLowestPriceListener getLowestPriceListener) {
        myOkHttp.get().url(NetworkUtil.GET_LOWEST_PRICE_URL + movieTitle).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                MovieScheduleEntity lowestPriceEntity = new Gson().fromJson(response, MovieScheduleEntity.class);
                if (lowestPriceEntity != null) getLowestPriceListener.onSuccess(lowestPriceEntity);
                else getLowestPriceListener.onNullResult();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getLowestPriceListener.onFailure(error_msg);
            }
        });
    }

    public void getLowestPriceByMovieId(int movieId, final GetLowestPriceListener getLowestPriceListener) {
        myOkHttp.get().url(NetworkUtil.GET_LOWEST_PRICE_URL2 + movieId).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.v("onSuccess", response);
                MovieScheduleEntity lowestPriceEntity = new Gson().fromJson(response, MovieScheduleEntity.class);
                if (lowestPriceEntity != null) getLowestPriceListener.onSuccess(lowestPriceEntity);
                else getLowestPriceListener.onNullResult();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getLowestPriceListener.onFailure(error_msg);
            }
        });
    }

    public void addWatchlistItem(String movieTitle, AddWatchlistItemListener addWatchlistItemListener) {
        UserDao dao = new UserDao();
        UserEntity userEntity = dao.getFirst();

        HashMap<String, String> params = new HashMap<>();
        params.put("watchlistOperation", "add");
        params.put("email", userEntity.getEmail());
        params.put("movieTitle", movieTitle);

        myOkHttp.post().url(NetworkUtil.WATCHLIST_MANAGEMENT_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                addWatchlistItemListener.onSuccess();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                addWatchlistItemListener.onFailure(error_msg);
            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetLowestPriceListener {
        void onSuccess(MovieScheduleEntity lowestPriceEntity);

        void onNullResult();

        void onFailure(String errorMessage);
    }

    public interface AddWatchlistItemListener {
        void onSuccess();

        void onFailure(String errorMessage);
    }
}
