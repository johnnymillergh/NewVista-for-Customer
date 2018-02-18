package com.jm.newvista.mvp.presenter;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.RateMovieModel;
import com.jm.newvista.mvp.view.RateMovieView;

/**
 * Created by Johnny on 2/16/2018.
 */

public class RateMoviePresenter extends BasePresenter<RateMovieModel, RateMovieView> {
    private RateMovieModel rateMovieModel;
    private RateMovieView rateMovieView;

    public RateMoviePresenter() {
        rateMovieModel = new RateMovieModel();
        super.BasePresenter(rateMovieModel);
    }

    public void displayUserAvatar() {
        UserEntity decodedCurrentUser = rateMovieModel.getDecodedUserInfo();
        if (decodedCurrentUser != null) {
            getView().onUpdateAvatar(decodedCurrentUser);
        }
    }

    public void submitUserReview() {
        rateMovieView = getView();

        // Get parameters
        String movieTitle = rateMovieView.getMovieTitle();
        int score = (int) (rateMovieView.getScores() * 2);
        String title = rateMovieView.getTitle();
        String text = rateMovieView.getText();
        boolean isSpoilers = rateMovieView.getIsSpoilers();

        // Prepare parameters
        UserReviewEntity userReviewEntity = new UserReviewEntity();
        userReviewEntity.setScore(score);
        userReviewEntity.setTitle(title);
        userReviewEntity.setText(text);
        userReviewEntity.setIsSpoilers(isSpoilers);

        rateMovieModel.postUserReview(movieTitle, userReviewEntity, new RateMovieModel.PostUserReviewListener() {
            @Override
            public void onPostSuccess(String message) {
                rateMovieView.makeToast(message);
            }

            @Override
            public void onPostFailure(String message) {
                rateMovieView.makeToast(message);
            }
        });
    }
}
