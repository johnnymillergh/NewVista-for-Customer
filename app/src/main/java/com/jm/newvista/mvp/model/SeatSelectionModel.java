package com.jm.newvista.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.bean.SeatEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/25/2018.
 */

public class SeatSelectionModel extends BaseModel {
    private MyOkHttp myOkHttp;

    public SeatSelectionModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    public void getMovieScheduleFromServer(int movieScheduleId,
                                           final GetMovieScheduleListener GetMovieScheduleListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("movieScheduleId", String.valueOf(movieScheduleId));
        myOkHttp.post().url(NetworkUtil.GET_MOVIE_SCHEDULE_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                MovieScheduleEntity movieScheduleEntity = new Gson().fromJson(response, MovieScheduleEntity.class);
                GetMovieScheduleListener.onSuccess(movieScheduleEntity);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                GetMovieScheduleListener.onFailure(error_msg);
            }
        });
    }

    public void getSeatFromServer(int auditoriumId, final GetSeatListener getSeatListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("satOperation", "getSeatOfAuditorium");
        params.put("auditoriumId", String.valueOf(auditoriumId));
        myOkHttp.post().url(NetworkUtil.SAT_MANAGEMENT_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<SeatEntity> seatEntities = new Gson().fromJson(response, new TypeToken<List<SeatEntity>>() {
                }.getType());
                getSeatListener.onSuccess(seatEntities);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getSeatListener.onFailure(error_msg);
            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetMovieScheduleListener {
        void onSuccess(MovieScheduleEntity movieScheduleEntity);

        void onFailure(String errorMessage);
    }

    public interface GetSeatListener {
        void onSuccess(List<SeatEntity> seatEntities);

        void onFailure(String errorMessage);
    }

}
