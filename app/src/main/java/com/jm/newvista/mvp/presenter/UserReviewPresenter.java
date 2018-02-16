package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.UserReviewModel;
import com.jm.newvista.mvp.view.UserReviewView;

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

    public void getAndDisplayUserReview() {

    }
}
