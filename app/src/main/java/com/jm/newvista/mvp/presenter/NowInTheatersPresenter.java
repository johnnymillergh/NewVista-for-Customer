package com.jm.newvista.mvp.presenter;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.NowInTheatersModel;
import com.jm.newvista.mvp.view.NowInTheatersView;
import com.jm.newvista.ui.adapter.NowInTheatersRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Johnny on 2/28/2018.
 */

public class NowInTheatersPresenter extends BasePresenter<NowInTheatersModel, NowInTheatersView> {
    private NowInTheatersModel nowInTheatersModel;
    private NowInTheatersView nowInTheatersView;

    public NowInTheatersPresenter() {
        nowInTheatersModel = new NowInTheatersModel();
        super.BasePresenter(nowInTheatersModel);
    }

    public void getAndDisplayMoviesInTheaters() {
        nowInTheatersView = getView();
        nowInTheatersModel.getMoviesInTheaters(new NowInTheatersModel.GetMoviesInTheatersListener() {
            @Override
            public void onSuccess(List<MovieEntity> nowInTheaters) {
                NowInTheatersRecyclerViewAdapter adapter = nowInTheatersView.onGetNowInTheatersRecyclerViewAdapter();
                adapter.setMoviesInTheaters(nowInTheaters);
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
