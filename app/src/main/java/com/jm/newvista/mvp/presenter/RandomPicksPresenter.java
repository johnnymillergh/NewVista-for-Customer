package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.RandomPicksModel;
import com.jm.newvista.mvp.view.RandomPicksView;

import java.util.List;

/**
 * Created by Johnny on 2/23/2018.
 */

public class RandomPicksPresenter extends BasePresenter<RandomPicksModel, RandomPicksView> {
    private RandomPicksModel randomPicksModel;
    private RandomPicksView randomPicksView;

    public RandomPicksPresenter() {
        randomPicksModel = new RandomPicksModel();
        super.BasePresenter(randomPicksModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void getAndDisplayRandomPicks() {
        randomPicksView = getView();
        new AsyncTask<Void, Void, List<MovieEntity>>() {
            @Override
            protected List<MovieEntity> doInBackground(Void... voids) {
                return randomPicksModel.getRandomPicksFromDB();
            }

            @Override
            protected void onPostExecute(List<MovieEntity> entities) {
                randomPicksView.onFinishPicks(entities);
            }
        }.execute();
    }

    public void getAndDisplayRecommendations() {
        randomPicksView = getView();
        randomPicksModel.getRecommendation(new RandomPicksModel.GetRecommendationListener() {
            @Override
            public void onSuccess(List<MovieEntity> recommendations) {
                randomPicksView.onFinishPicks(recommendations);
            }

            @Override
            public void onNullResult() {
                randomPicksView.onNullRecommendations();
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }
}
