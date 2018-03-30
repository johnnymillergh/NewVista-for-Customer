package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.bean.SeatEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

import io.github.lh911002.seatview.seat.Seat;

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

    public void postOrderInfo(int movieScheduleId, int ticketAmount, List<Seat> selectedSeats,
                              PostOrderInfoListener postOrderInfoListener) {
        UserDao dao = new UserDao();
        UserEntity currentUser = dao.getFirst();

        HashMap<String, String> params = new HashMap<>();
        params.put("orderOperation", "takeOrder");
        params.put("email", currentUser.getEmail());
        params.put("movieScheduleId", String.valueOf(movieScheduleId));
        params.put("ticketAmount", String.valueOf(ticketAmount));
        params.put("seatId", selectedSeats.get(0).id);

        myOkHttp.post().url(NetworkUtil.ORDER_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                CustomerOrderEntity orderEntity = new Gson().fromJson(response, CustomerOrderEntity.class);
                postOrderInfoListener.onSuccess(orderEntity);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                postOrderInfoListener.onFailure(error_msg);
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

    public interface PostOrderInfoListener {
        void onSuccess(CustomerOrderEntity orderEntity);

        void onFailure(String errorMessage);
    }
}
