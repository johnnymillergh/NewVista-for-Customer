package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 3/24/2018.
 */

public class MovieScheduleDetailModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public void getMovieScheduleDetail(MovieScheduleEntity entity, GetMovieScheduleDetailListener
            getMovieScheduleDetailListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("auditoriumTheaterId", String.valueOf(entity.getAuditoriumTheaterId()));
        params.put("movieTitle", entity.getMovieTitle());
        Log.v("getMovieScheduleDetail", params.toString());

        myOkHttp.post().url(NetworkUtil.GET_MOVIE_SCHEDULE_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<MovieScheduleEntity> entities = new Gson().fromJson(response,
                        new TypeToken<List<MovieScheduleEntity>>() {
                        }.getType());
                if (entities.size() != 0) {
                    getMovieScheduleDetailListener.onSuccess(entities);
                } else {
                    getMovieScheduleDetailListener.onNullResult();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getMovieScheduleDetailListener.onFailure(error_msg);
            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetMovieScheduleDetailListener {
        void onSuccess(List<MovieScheduleEntity> movieSchedules);

        void onNullResult();

        void onFailure(String errorMessage);
    }
}
