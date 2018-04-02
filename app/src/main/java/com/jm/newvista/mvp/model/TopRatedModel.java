package com.jm.newvista.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieRankingEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.List;

/**
 * Created by Johnny on 3/17/2018.
 */

public class TopRatedModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public void getTopRated(GetTopRatedListener getTopRatedListener) {
        myOkHttp.get().url(NetworkUtil.GET_TOP_RATED_URL).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<MovieRankingEntity> topRated = new Gson().fromJson(response,
                        new TypeToken<List<MovieRankingEntity>>() {
                        }.getType());
                getTopRatedListener.onSuccess(topRated);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getTopRatedListener.onFailure(error_msg);
            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetTopRatedListener {
        void onSuccess(List<MovieRankingEntity> topRated);

        void onNullResult();

        void onFailure(String errorMessage);
    }
}
