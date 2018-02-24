package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/16/2018.
 */

public class UserReviewModel extends BaseModel {
    private MyOkHttp myOkHttp;

    public UserReviewModel() {
        myOkHttp = NetworkUtil.myOkHttp;
    }

    public void getUserReview(String movieTitle, final GetUserReviewListener getUserReviewListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userReviewOperation", "getJson");
        params.put("movieTitle", movieTitle);

        myOkHttp.post().url(NetworkUtil.USER_REVIEW_MANAGEMENT_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<UserReviewEntity> userReviews = new Gson().fromJson(response,
                        new TypeToken<List<UserReviewEntity>>() {
                        }.getType());
                Log.v("UserReviews", response);
                getUserReviewListener.onSuccess(userReviews);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getUserReviewListener.onFailure(error_msg);
            }
        });
    }

    public void getUserReviewNewestFirst(String movieTitle, final GetUserReviewListener getUserReviewListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userReviewOperation", "getJson");
        params.put("movieTitle", movieTitle);

        myOkHttp.post().url(NetworkUtil.USER_REVIEW_MANAGEMENT_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<UserReviewEntity> userReviews = new Gson().fromJson(response,
                        new TypeToken<List<UserReviewEntity>>() {
                        }.getType());
                Log.v("UserReviews", response);
                List<UserReviewEntity> userReviewNewestFirst = new ArrayList<>();
                getUserReviewListener.onSuccess(userReviews);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getUserReviewListener.onFailure(error_msg);
            }
        });
    }

    @Override
    public void cancel() {

    }

    public interface GetUserReviewListener {
        void onSuccess(List<UserReviewEntity> userReviews);

        void onFailure(String errorMessage);
    }
}
