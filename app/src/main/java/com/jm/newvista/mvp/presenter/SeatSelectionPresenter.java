package com.jm.newvista.mvp.presenter;

import android.content.Intent;

import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.SeatSelectionModel;
import com.jm.newvista.mvp.view.SeatSelectionView;

/**
 * Created by Johnny on 2/25/2018.
 */

public class SeatSelectionPresenter extends BasePresenter<SeatSelectionModel, SeatSelectionView> {
    private SeatSelectionModel seatSelectionModel;
    private SeatSelectionView seatSelectionView;

    public SeatSelectionPresenter() {
        seatSelectionModel = new SeatSelectionModel();
        super.BasePresenter(seatSelectionModel);
    }

    public void updateToolBar() {
        seatSelectionView = getView();
        Intent intent = seatSelectionView.onGetIntent();
        String movieTitle = intent.getStringExtra("theaterName");
        seatSelectionView.onUpdateToolBar(movieTitle);
    }

    public void getAndDisplayMovieSchedule() {
        Intent intent = seatSelectionView.onGetIntent();
        int movieScheduleId = intent.getIntExtra("movieScheduleId", -1);
        seatSelectionModel.getMovieScheduleFromServer(movieScheduleId, new SeatSelectionModel
                .GetMovieScheduleListener() {
            @Override
            public void onSuccess(MovieScheduleEntity movieScheduleEntity) {
                getView().onUpdateBasicInfo(movieScheduleEntity);
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }
}
