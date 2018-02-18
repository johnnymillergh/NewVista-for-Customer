package com.jm.newvista.mvp.presenter;

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
            @Override
            public void onSuccess(List<UserReviewEntity> userReviews) {
                userReviewView.onSetUserReviewList(userReviews);
                Log.v("UserReviewFragment", userReviews.size() + "");
            }

            @Override
            public void onFailure(String errorMessage) {
                userReviewView.makeToast(errorMessage);
            }
        });
    }
}
