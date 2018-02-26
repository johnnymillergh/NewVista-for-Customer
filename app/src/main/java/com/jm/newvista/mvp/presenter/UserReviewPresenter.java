package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.UserReviewModel;
import com.jm.newvista.mvp.view.UserReviewView;

import java.util.List;

/**
 * Created by Johnny on 2/16/2018.
 */

public class UserReviewPresenter extends BasePresenter<UserReviewModel, UserReviewView> {
    private UserReviewModel userReviewModel;
    private UserReviewView userReviewView;

    public UserReviewPresenter() {
        userReviewModel = new UserReviewModel();
        super.BasePresenter(userReviewModel);
    }

    public void getAndDisplayUserReview(String movieTitle) {
        userReviewView = getView();
        userReviewModel.getUserReview(movieTitle, new UserReviewModel.GetUserReviewListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onSuccess(final List<UserReviewEntity> userReviews) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(1000 * 3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        userReviewView.onSetUserReviewList(userReviews);
                    }
                }.execute();
            }

            @Override
            public void onFailure(String errorMessage) {
                userReviewView.makeToast(errorMessage);
                userReviewView.onFailLoadingUserReview();
            }
        });
    }
}
