package com.jm.newvista.mvp.view;

import android.content.Intent;
import android.widget.FrameLayout;

import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BaseView;

import java.util.List;

import io.github.lh911002.seatview.seat.Seat;
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

    void onMakeToast(String message);

    List<Seat> onGetSelectedSeats();

    MovieScheduleEntity onGetCurrentMovieSchedule();

    void onStartPaymentActivity(CustomerOrderEntity orderEntity);
}
