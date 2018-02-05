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

import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/5/2018.
 */

@SuppressWarnings("unchecked")
public class MovieModel extends BaseModel {
    MyOkHttp myOkHttp;

    public MovieModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    public void getAndSaveMovie(final MovieModelCallback movieModelCallback) {
        // Prepare parameter
        HashMap<String, String> params = new HashMap();
        params.put("movieOperation", "getAll");
        String url = NetworkUtil.GET_MOVIE_URL;
        // About to post
        Log.d("getAndSaveMovie", "myOkHttp==null: " + (myOkHttp == null));
        myOkHttp.post().url(url).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.d("getAndSaveMovie", "onSuccess");
                List<MovieEntity> entities = new Gson().fromJson(response,
                        new TypeToken<List<MovieEntity>>() {
                        }.getType());
                saveMovie(entities, movieModelCallback);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.d("getAndSaveMovie", "onFailure" + error_msg);
            }
        });
    }

    private void saveMovie(List<MovieEntity> entities, MovieModelCallback movieModelCallback) {
        MovieDao dao = new MovieDao();
        if (!dao.isEmpty()) {
            dao.deleteAll();
        }
        for (MovieEntity entity : entities) {
            dao.save(entity);
        }
        movieModelCallback.onSaveMovieFinish();
    }

    public List<MovieEntity> getMovieFromDB() {
        return new MovieDao().getAll();
    }

    @Override
    public void cancel() {
        Log.v("cancel()", getClass().toString());
        myOkHttp.cancel(this);
    }

    public interface MovieModelCallback {
        void onSaveMovieFinish();
    }
}
