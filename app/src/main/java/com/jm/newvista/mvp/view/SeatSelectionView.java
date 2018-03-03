package com.jm.newvista.mvp.view;

import android.content.Intent;
import android.widget.FrameLayout;

import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BaseView;

import io.github.lh911002.seatview.seat.SeatView;

/**
 * Created by Johnny on 2/25/2018.
 */

public interface SeatSelectionView extends BaseView {
    Intent onGetIntent();

    void onUpdateToolBar(String movieTitle);

    void onUpdateBasicInfo(MovieScheduleEntity movieScheduleEntity);

    SeatView onUpdateSeatView();

    FrameLayout onGetSeatViewContainer();

    void onDisplayLoadingDialog();

    void onDismissLoadingDialog();

    void onSetSeatView(SeatView seatView);
}
