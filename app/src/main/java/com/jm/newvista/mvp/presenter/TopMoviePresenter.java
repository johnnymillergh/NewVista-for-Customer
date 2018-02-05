package com.jm.newvista.mvp.presenter;

import android.util.Log;

import com.jm.newvista.bean.TopMovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopMovieModel;
import com.jm.newvista.mvp.view.TopMovieView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/2/2018.
 */

public class TopMoviePresenter extends BasePresenter<TopMovieModel, TopMovieView> {
    private TopMovieModel topMovieModel;
    private TopMovieView topMovieView;

    public TopMoviePresenter() {
        topMovieModel = new TopMovieModel();
        super.BasePresenter(topMovieModel);
    }

    public void getTopMovieAndDisplay() {
        topMovieModel.getTopMovieAndSave(new TopMovieModel.TopMovieModelCallback() {
            @Override
            public void onFinishLoadingTitle(List<TopMovieEntity> entities) {
                List<String> topMovieTitles = getView().getViewPagerAdapter().getTopMovieTitles();
                for (TopMovieEntity entity : entities) {
                    topMovieTitles.add(entity.getMovieTitle());
                }
                getView().getViewPagerAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFinishLoadingPoster(TopMovieEntity entity) {
                HashMap<Integer, String> topMoviePoster = getView().getViewPagerAdapter().getTopMoviePoster();
                topMoviePoster.put(entity.getId(), entity.getPosterStr());
                Log.d("onFinishLoadingPoster", "entity " + entity.getPosterStr().substring(10));
                getView().getViewPagerAdapter().notifyDataSetChanged();
            }
        });
    }
}
