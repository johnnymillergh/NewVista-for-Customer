package com.jm.newvista.mvp.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jm.newvista.bean.MovieRankingEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopSellingModel;
import com.jm.newvista.mvp.view.TopSellingView;
import com.jm.newvista.ui.adapter.TopSellingRecyclerViewAdapter;
import com.jm.newvista.util.ApplicationUtil;

import java.util.List;

public class TopSellingPresenter extends BasePresenter<TopSellingModel, TopSellingView> {
    private TopSellingModel topSellingModel;
    private TopSellingView topSellingView;

    private boolean isChinese = false;

    public TopSellingPresenter() {
        topSellingModel = new TopSellingModel();
        super.BasePresenter(topSellingModel);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ApplicationUtil.getContext());
        String language = prefs.getString("example_list", "1");
        if (!language.equals("1")) isChinese = true;
    }

    public void getAndDisplayTopSelling() {
        topSellingView = getView();
        topSellingModel.getTopSelling(new TopSellingModel.GetTopSellingListener() {
            @Override
            public void onSuccess(List<MovieRankingEntity> topSelling) {
                if (!isChinese) {
                    TopSellingRecyclerViewAdapter adapter = topSellingView.onGetTopSellingRecyclerViewAdapter();
                    adapter.setTopSellingMovies(topSelling);
                    adapter.notifyDataSetChanged();
                } else {
                    topSellingModel.convertToChineseTitle(topSelling, topSelling1 -> {
                        TopSellingRecyclerViewAdapter adapter = topSellingView.onGetTopSellingRecyclerViewAdapter();
                        adapter.setTopSellingMovies(topSelling1);
                        adapter.notifyDataSetChanged();
                    });
                }
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
