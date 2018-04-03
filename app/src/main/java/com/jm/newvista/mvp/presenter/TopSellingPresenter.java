package com.jm.newvista.mvp.presenter;

import com.jm.newvista.bean.MovieRankingEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopSellingModel;
import com.jm.newvista.mvp.view.TopSellingView;
import com.jm.newvista.ui.adapter.TopSellingRecyclerViewAdapter;

import java.util.List;

public class TopSellingPresenter extends BasePresenter<TopSellingModel, TopSellingView> {
    private TopSellingModel topSellingModel;
    private TopSellingView topSellingView;

    public TopSellingPresenter() {
        topSellingModel = new TopSellingModel();
        super.BasePresenter(topSellingModel);
    }

    public void getAndDisplayTopSelling() {
        topSellingView = getView();
        topSellingModel.getTopSelling(new TopSellingModel.GetTopSellingListener() {
            @Override
            public void onSuccess(List<MovieRankingEntity> topSelling) {
                TopSellingRecyclerViewAdapter adapter = topSellingView.onGetTopSellingRecyclerViewAdapter();
                adapter.setTopSellingMovies(topSelling);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNullResult() {
                topSellingView.onMakeToast("Null Result: Top Selling");
            }

            @Override
            public void onFailure(String errorMessage) {
                topSellingView.onMakeToast(errorMessage);
            }
        });
    }
}
