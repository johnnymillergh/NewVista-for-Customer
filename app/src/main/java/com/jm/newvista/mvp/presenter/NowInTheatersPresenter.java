package com.jm.newvista.mvp.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.NowInTheatersModel;
import com.jm.newvista.mvp.view.NowInTheatersView;
import com.jm.newvista.ui.adapter.NowInTheatersRecyclerViewAdapter;
import com.jm.newvista.util.ApplicationUtil;

import java.util.List;

/**
 * Created by Johnny on 2/28/2018.
 */

public class NowInTheatersPresenter extends BasePresenter<NowInTheatersModel, NowInTheatersView> {
    private NowInTheatersModel nowInTheatersModel;
    private NowInTheatersView nowInTheatersView;

    private boolean isChinese = false;

    public NowInTheatersPresenter() {
        nowInTheatersModel = new NowInTheatersModel();
        super.BasePresenter(nowInTheatersModel);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ApplicationUtil.getContext());
        String language = prefs.getString("example_list", "1");
        if (!language.equals("1")) isChinese = true;
    }

    public void getAndDisplayMoviesInTheaters() {
        nowInTheatersView = getView();
        nowInTheatersModel.getMoviesInTheaters(new NowInTheatersModel.GetMoviesInTheatersListener() {
            @Override
            public void onSuccess(List<MovieEntity> nowInTheaters) {
                if (!isChinese) {
                    NowInTheatersRecyclerViewAdapter adapter = nowInTheatersView
                            .onGetNowInTheatersRecyclerViewAdapter();
                    adapter.setMoviesInTheaters(nowInTheaters);
                    adapter.notifyDataSetChanged();
                } else {
                    nowInTheatersModel.convertToChineseTitle(nowInTheaters, nowInTheaters1 -> {
                        NowInTheatersRecyclerViewAdapter adapter = nowInTheatersView
                                .onGetNowInTheatersRecyclerViewAdapter();
                        adapter.setMoviesInTheaters(nowInTheaters1);
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
