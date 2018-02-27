package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.jm.newvista.bean.TopMovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopMovieModel;
import com.jm.newvista.mvp.view.TopMovieView;
import com.jm.newvista.util.ImageUtil;

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
        topMovieView = getView();
        topMovieModel.getTopMovieAndSave(new TopMovieModel.TopMovieModelCallback() {
            @Override
            public void onFinishLoadingTitle(List<TopMovieEntity> entities) {
                List<String> topMovieTitles = topMovieView.getViewPagerAdapter().getTopMovieTitles();
                for (TopMovieEntity entity : entities) {
                    topMovieTitles.add(entity.getMovieTitle());
                }
                topMovieView.getViewPagerAdapter().notifyDataSetChanged();
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            public void onFinishLoadingPoster(final TopMovieEntity entity) {
//                final HashMap<Integer, byte[]> topMoviePoster = topMovieView.getViewPagerAdapter().getTopMoviePoster();
//                new AsyncTask<Void, Void, byte[]>() {
//                    @Override
//                    protected byte[] doInBackground(Void... voids) {
//                        return ImageUtil.decode(entity.getPosterStr());
//                    }
//
//                    @Override
//                    protected void onPostExecute(byte[] bytes) {
//                        topMoviePoster.put(entity.getId(), bytes);
//                        topMovieView.getViewPagerAdapter().notifyDataSetChanged();
//                    }
//                }.execute();
            }
        });
    }
}
