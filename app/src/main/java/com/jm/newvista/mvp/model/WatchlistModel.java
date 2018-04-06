package com.jm.newvista.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.bean.WatchlistEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WatchlistModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public void getWatchlist(GetWatchlistListener getWatchlistListener) {
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getFirst();

        HashMap<String, String> params = new HashMap<>();
        params.put("watchlistOperation", "getAll");
        params.put("email", userEntity.getEmail());

        myOkHttp.post().url(NetworkUtil.WATCHLIST_MANAGEMENT_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<WatchlistEntity> entities = new Gson().fromJson(response,
                        new TypeToken<List<WatchlistEntity>>() {
                        }.getType());

                if (entities.size() > 0) getWatchlistListener.onSuccess(entities);
                else getWatchlistListener.onNullResult();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getWatchlistListener.onFailure(error_msg);
            }
        });
    }

    public void convertWatchlistToMovieEntity(List<WatchlistEntity> watchlist, ConvertWatchlistListener
            convertWatchlistListener) {
        MovieDao movieDao = new MovieDao();
        List<MovieEntity> movies = new ArrayList<>();

        for (WatchlistEntity we : watchlist) {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setTitle(we.getMovieTitle());

            movieEntity = movieDao.queryMovieByTitle(movieEntity);
            if (movieEntity != null) movies.add(movieEntity);
        }

        if (movies.size() > 0) convertWatchlistListener.onSuccess(movies);
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetWatchlistListener {
        void onSuccess(List<WatchlistEntity> watchlist);

        void onNullResult();

        void onFailure(String errorMessage);
    }

    public interface ConvertWatchlistListener {
        void onSuccess(List<MovieEntity> movies);
    }
}
