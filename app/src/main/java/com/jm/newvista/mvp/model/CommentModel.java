package com.jm.newvista.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

public class CommentModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public void getUserReview(GetUserReviewListener getUserReviewListener) {
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getFirst();

        HashMap<String, String> params = new HashMap<>();
        params.put("userReviewOperation", "getJson");
        params.put("email", userEntity.getEmail());

        myOkHttp.post().url(NetworkUtil.USER_REVIEW_MANAGEMENT_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<UserReviewEntity> entities = new Gson().fromJson(response,
                        new TypeToken<List<UserReviewEntity>>() {
                        }.getType());

                if (entities.size() != 0) getUserReviewListener.onSuccess(entities);
                else getUserReviewListener.onNullResult();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getUserReviewListener.onFailure(statusCode + ":" + error_msg);
            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetUserReviewListener {
        void onSuccess(List<UserReviewEntity> userReviews);

        void onNullResult();

        void onFailure(String errorMessage);
    }
}
