package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.SeatSelectionModel;
import com.jm.newvista.mvp.view.SeatSelectionView;
import com.jm.newvista.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import io.github.lh911002.seatview.seat.OnClickSeatListener;
import io.github.lh911002.seatview.seat.Seat;
import io.github.lh911002.seatview.seat.SeatImages;
import io.github.lh911002.seatview.seat.SeatRow;
import io.github.lh911002.seatview.seat.SeatView;

/**
 * Created by Johnny on 2/25/2018.
 */

public class SeatSelectionPresenter extends BasePresenter<SeatSelectionModel, SeatSelectionView>
        implements OnClickSeatListener {
    private SeatSelectionModel seatSelectionModel;
    private SeatSelectionView seatSelectionView;

    private List<Seat> seats = new ArrayList<>();

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

    @SuppressLint("StaticFieldLeak")
    public void getAndDisplaySeat() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                seatSelectionView.onDisplayLoadingDialog();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                SeatView seatView = seatSelectionView.onUpdateSeatView();
                seatView.initSeatView("Auditorium 1".toUpperCase(), new SeatImages(ApplicationUtil.context
                        .getResources()), querySeatMap(), 4);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
                seatView.setLayoutParams(params);
                FrameLayout seatViewContainer = getView().onGetSeatViewContainer();
                seatViewContainer.addView(seatView);
                Toast.makeText(ApplicationUtil.context, "Auditorium 1", Toast.LENGTH_SHORT).show();
                seatSelectionView.onDismissLoadingDialog();
            }
        }.execute();
    }

    private List<SeatRow> querySeatMap() {
        List<SeatRow> seatRows = new ArrayList<>();
        for (int rowCount = 0; rowCount < 20; rowCount++) {
            SeatRow seatRow = new SeatRow();
            seatRow.rowName = String.valueOf(rowCount);
            List<Seat> seats = new ArrayList<>();
            for (int colCount = 0; colCount < 15; colCount++) {
                Seat seat = new Seat();
                seat.columnName = String.valueOf(colCount + 1);
                if (rowCount == 5) {
                    seat.status = Seat.STATUS.CORRIDOR;
                } else {
                    seat.status = Seat.STATUS.SELECTABLE;
                }
                if (colCount == 5 & rowCount != 5) {
                    seat.status = Seat.STATUS.UNSELECTABLE;
                }
                seat.id = String.valueOf(seat);
                seats.add(seat);
            }
            seatRow.seats = seats;
            seatRows.add(seatRow);
        }
        return seatRows;
    }

    @Override
    public void releaseSeat(Seat canceledSeat) {

    }

    @Override
    public void lockSeat(Seat selectedSeat) {

    }

    @Override
    public void onExceedMaxSelectionCount(int maxSelectionCount) {

    }
}
