package com.jm.newvista.mvp.presenter;

import com.jm.newvista.bean.MovieRatingEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopRatedModel;
import com.jm.newvista.mvp.view.TopRatedView;
import com.jm.newvista.ui.adapter.TopRatedRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Johnny on 3/17/2018.
 */

public class TopRatedPresenter extends BasePresenter<TopRatedModel, TopRatedView> {
    private TopRatedModel topRatedModel;
    private TopRatedView topRatedView;

    public TopRatedPresenter() {
        topRatedModel = new TopRatedModel();
        super.BasePresenter(topRatedModel);
    }

    public void getAndDisplayTopRated(){
        topRatedView = getView();
        topRatedModel.getTopRated(new TopRatedModel.GetTopRatedListener() {
            @Override
            public void onSuccess(List<MovieRatingEntity> topRated) {
                TopRatedRecyclerViewAdapter adapter = topRatedView.onGetTopRatedRecyclerViewAdapter();
                adapter.setTopRatedMovies(topRated);
                adapter.notifyDataSetChanged();
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
