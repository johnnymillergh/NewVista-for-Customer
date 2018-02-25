package com.jm.newvista.mvp.presenter;

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

    public void getAndDisplaySeat() {

    }
}
