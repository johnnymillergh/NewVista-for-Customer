package com.jm.newvista.mvp.model;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jm.newvista.R;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.ApplicationUtil;
import com.jm.newvista.util.ImageUtil;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Johnny on 2/16/2018.
 */

public class RateMovieModel extends BaseModel {
    private MyOkHttp myOkHttp;
    private UserEntity currentUser;

    public RateMovieModel() {
        myOkHttp = NetworkUtil.myOkHttp;
        UserModel userModel = new UserModel();
        currentUser = userModel.getFromDB();
    }

    public void postUserReview(String movieTitle, UserReviewEntity userReviewEntity,
                               final PostUserReviewListener postUserReviewListener) {
        if (currentUser != null) {
            HashMap<String, String> params = new HashMap<>();
            params.put("userReviewOperation", "Add");
            params.put("email", currentUser.getEmail());
            params.put("movieTitle", movieTitle);
            params.put("score", userReviewEntity.getScore() + "");
            params.put("userReviewTitle", userReviewEntity.getTitle());
            params.put("text", userReviewEntity.getText());
            params.put("isSpoilers", userReviewEntity.getIsSpoilers() + "");
            Log.v("postUserReview", params.toString());

            myOkHttp.post().url(NetworkUtil.USER_REVIEW_MANAGEMENT_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    Log.v("postUserReview", response);
                    if (response.contains("success")) {
                        postUserReviewListener.onPostSuccess(ApplicationUtil.getContext()
                                .getString(R.string.login_prompt));
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    Log.v("postUserReview", error_msg);
                    postUserReviewListener.onPostFailure(error_msg);
                }
            });
        } else {
            postUserReviewListener.onPostFailure(ApplicationUtil.getContext().getString(R.string.login_prompt));
        }
    }

    @SuppressWarnings("unchecked")
    public UserEntity getDecodedUserInfo() {
        FutureTask<UserEntity> decodingTask = new FutureTask(new Callable<UserEntity>() {
            @Override
            public UserEntity call() throws Exception {
                currentUser.setAvatar(ImageUtil.decode(currentUser.getAvatarStr()));
                return currentUser;
            }
        });
        new Thread(decodingTask).start();
        try {
            return decodingTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface PostUserReviewListener {
        void onPostSuccess(String message);

        void onPostFailure(String message);
    }
}
