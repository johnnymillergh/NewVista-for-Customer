package com.jm.newvista.mvp.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jm.newvista.bean.MovieRankingEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopRatedModel;
import com.jm.newvista.mvp.view.TopRatedView;
import com.jm.newvista.ui.adapter.TopRatedRecyclerViewAdapter;
import com.jm.newvista.util.ApplicationUtil;

import java.util.List;

/**
 * Created by Johnny on 3/17/2018.
 */

public class TopRatedPresenter extends BasePresenter<TopRatedModel, TopRatedView> {
    private TopRatedModel topRatedModel;
    private TopRatedView topRatedView;

    private boolean isChinese = false;

    public TopRatedPresenter() {
        topRatedModel = new TopRatedModel();
        super.BasePresenter(topRatedModel);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ApplicationUtil.getContext());
        String language = prefs.getString("example_list", "1");
        if (!language.equals("1")) isChinese = true;
    }

    public void getAndDisplayTopRated() {
        topRatedView = getView();
        topRatedModel.getTopRated(new TopRatedModel.GetTopRatedListener() {
            @Override
            public void onSuccess(List<MovieRankingEntity> topRated) {
                if (!isChinese) {
                    TopRatedRecyclerViewAdapter adapter = topRatedView.onGetTopRatedRecyclerViewAdapter();
                    adapter.setTopRatedMovies(topRated);
                    adapter.notifyDataSetChanged();
                } else {
                    topRatedModel.convertToChineseTitle(topRated, topRated1 -> {
                        TopRatedRecyclerViewAdapter adapter = topRatedView.onGetTopRatedRecyclerViewAdapter();
                        adapter.setTopRatedMovies(topRated1);
                        adapter.notifyDataSetChanged();
                    });
                }
            }

            @Override
            public void onNullResult() {

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }
}
