package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Johnny on 2/23/2018.
 */

public class RandomPicksModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public List<MovieEntity> getRandomPicksFromDB() {
        MovieDao movieDao = new MovieDao();
        List<MovieEntity> fullList = movieDao.getAll();
        List<MovieEntity> resultList = new ArrayList<>();
        Random random = new Random();
        while (resultList.size() <= 10) {
            int randomIndex = random.nextInt(fullList.size());
            MovieEntity randomMovieEntity = fullList.get(randomIndex);
            if (randomMovieEntity != null)
                if (!resultList.contains(fullList.get(randomIndex))) resultList.add(fullList.get(randomIndex));
        }
        return resultList;
    }

    public void getRecommendation(GetRecommendationListener getRecommendationListener) {
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getFirst();
        if (userEntity != null) {
            HashMap<String, String> params = new HashMap<>();
            params.put("email", userEntity.getEmail());
            myOkHttp.post().url(NetworkUtil.GET_RECOMMENDATION_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    List<MovieEntity> recommendations = new Gson().fromJson(response,
                            new TypeToken<List<MovieEntity>>() {
                            }.getType());

                    if (recommendations.size() != 0) {
                        getRecommendationListener.onSuccess(convertRecommendation(recommendations));
                    } else {
                        getRecommendationListener.onNullResult();
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    getRecommendationListener.onFailure(error_msg);
                }
            });
        }
    }

    private List<MovieEntity> convertRecommendation(List<MovieEntity> recommendations) {
        MovieDao movieDao = new MovieDao();
        List<MovieEntity> movieEntities = new ArrayList<>();

        for (MovieEntity me : recommendations) {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setTitle(me.getTitle());
            movieEntity = movieDao.queryMovieByTitle(movieEntity);
            movieEntities.add(movieEntity);
        }

        return movieEntities;
    }

    public interface GetRecommendationListener {
        void onSuccess(List<MovieEntity> recommendations);

        void onNullResult();

        void onFailure(String errorMessage);
    }

    @Override
    public void cancel() {
        Log.v("cancel", getClass().toString());
        myOkHttp.cancel(this);
    }
}
