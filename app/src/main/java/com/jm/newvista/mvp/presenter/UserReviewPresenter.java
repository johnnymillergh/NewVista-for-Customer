package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.UserReviewModel;
import com.jm.newvista.mvp.view.UserReviewView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;

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
                if (userReviews.size() != 0) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                Thread.sleep(1000 * 2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            userReviewView.onSetUserReviewList(userReviews);
                            userReviewView.onSetUserReviewsCount(userReviews.size());
                        }
                    }.execute();
                } else {
                    userReviewView.onSetUserReviewList(userReviews);
                    userReviewView.onSetUserReviewsCount(0);
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                userReviewView.makeToast(errorMessage);
                userReviewView.onFailLoadingUserReview();
            }
        });
    }

    public void displayUserReviewStatistics(ColumnChartData data, List<UserReviewEntity> userReviews) {
        // Prepare the statistic: Mapping: Score ---> Count
        HashMap<Integer, Integer> statistic = new HashMap<>();
        for (UserReviewEntity ure : userReviews) {
            int score = ure.getScore();
            if (!statistic.containsKey(score)) {
                statistic.put(score, 1);
            } else {
                int count = statistic.get(score);
                statistic.put(score, ++count);
            }
        }

        // Average score
        float totalScore = 0;
        float totalUserCount = 0;
        float averageScore = 0;
        for (Map.Entry<Integer, Integer> entry : statistic.entrySet()) {
            totalScore += entry.getKey() * entry.getValue();
            totalUserCount += entry.getValue();
        }
        averageScore = totalScore / totalUserCount;

        int index = 0;
        for (Column column : data.getColumns()) {
            int userCount = 0;
            if (statistic.containsKey(index)) {
                userCount = statistic.get(index);
            }
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget(userCount);
            }
            index++;
        }

        userReviewView.onUpdateChart(averageScore);
    }
}
