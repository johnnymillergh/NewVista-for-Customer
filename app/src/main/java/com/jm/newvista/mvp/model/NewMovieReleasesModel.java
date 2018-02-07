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
    int flag = 0;

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
        for (int i = startIndex; i < endIndex; i++) {
            newMovieList.add(entities.get(i));
        }
        return newMovieList;
    }

    public void getAndSaveNewMoviePoster(final NewMovieReleasesCallbackListener newMovieReleasesCallbackListener) {
        int flag = 0;
        for (MovieEntity entity : newMovieList) {
            if (entity.getPosterStr() == null) {
                HashMap<String, String> params = new HashMap<>();
                params.put("movieOperation", "getPoster");//movieOperation=getPoster&title=Blade+Runner+2049+%282017%29
                params.put("title", entity.getTitle());
                myOkHttp.post().url(NetworkUtil.GET_MOVIE_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        MovieEntity movieEntity = new Gson().fromJson(response, MovieEntity.class);
                        updateNewMoviePoster(movieEntity, newMovieReleasesCallbackListener);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
            } else {
                flag++;
            }
        }
        if (flag == newMovieList.size()) {
            newMovieReleasesCallbackListener.onFinishSavingPoster();
        }
    }

    private void updateNewMoviePoster(MovieEntity entity, NewMovieReleasesCallbackListener
            newMovieReleasesCallbackListener) {
        Log.v("updateNewMoviePoster", getClass() + ", movie poster saved");
        MovieDao dao = new MovieDao();
        dao.updatePosterStrByTitle(entity);
        flag++;
        if (flag == newMovieList.size()) {
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
