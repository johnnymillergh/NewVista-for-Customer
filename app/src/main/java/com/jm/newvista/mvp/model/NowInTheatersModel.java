package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.List;

/**
 * Created by Johnny on 2/28/2018.
 */

public class NowInTheatersModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public void getMoviesInTheaters(final GetMoviesInTheatersListener getMoviesInTheatersListener) {
        myOkHttp.get().url(NetworkUtil.GET_NOW_IN_THEATERS_URL).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<MovieEntity> nowInTheaters = new Gson().fromJson(response,
                        new TypeToken<List<MovieEntity>>() {
                        }.getType());
                if (nowInTheaters != null) getMoviesInTheatersListener.onSuccess(nowInTheaters);
                else getMoviesInTheatersListener.onNullResult();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getMoviesInTheatersListener.onFailure(error_msg);
            }
        });
    }

    public void convertToChineseTitle(List<MovieEntity> nowInTheaters, ConvertListener convertListener) {
        MovieDao movieDao = new MovieDao();
        for (int i = 0; i < nowInTheaters.size(); i++) {
            MovieEntity mre = nowInTheaters.get(i);
            String titleCHS = movieDao.findTitleCHSByTitle(mre.getTitle());
            mre.setTitle(titleCHS);
        }
        convertListener.onFinish(nowInTheaters);
    }

    @Override
    public void cancel() {
        Log.v("cancel", getClass().toString());
        myOkHttp.cancel(this);
    }

    public interface GetMoviesInTheatersListener {
        void onSuccess(List<MovieEntity> nowInTheaters);

        void onNullResult();

        void onFailure(String errorMessage);
    }

    public interface ConvertListener{
        void onFinish(List<MovieEntity> nowInTheaters);
    }
}
