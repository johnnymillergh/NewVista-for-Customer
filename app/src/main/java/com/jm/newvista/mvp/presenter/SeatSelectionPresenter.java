package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.bean.SeatEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.SeatSelectionModel;
import com.jm.newvista.mvp.view.SeatSelectionView;
import com.jm.newvista.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import io.github.lh911002.seatview.seat.Seat;
import io.github.lh911002.seatview.seat.SeatImages;
import io.github.lh911002.seatview.seat.SeatRow;
import io.github.lh911002.seatview.seat.SeatView;

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
                seatSelectionView.onMakeToast(errorMessage);
            }
        });
    }

    public void getAndDisplaySeat() {
        Intent intent = seatSelectionView.onGetIntent();
        int auditoriumId = intent.getIntExtra("auditoriumId", -1);

        seatSelectionView.onDisplayLoadingDialog();

        seatSelectionModel.getSeatFromServer(auditoriumId, new SeatSelectionModel.GetSeatListener() {
            @Override
            public void onSuccess(List<SeatEntity> seatEntities) {
                initSeatView(seatEntities);
            }

            @Override
            public void onFailure(String errorMessage) {
                seatSelectionView.onMakeToast(errorMessage);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("unchecked")
    private void initSeatView(List<SeatEntity> seatEntities) {
        Intent intent = seatSelectionView.onGetIntent();
        final String auditoriumName = intent.getStringExtra("auditoriumName");

        new AsyncTask<List<SeatEntity>, Void, List<SeatRow>>() {
            @Override
            protected List<SeatRow> doInBackground(List<SeatEntity>[] lists) {
                List<SeatEntity> seatEntities = lists[0];
                List<SeatRow> seatRows = new ArrayList<>();

                int maxRowCount = getMaxRowNumber(seatEntities);
                int maxColCount = getMaxColNumber(seatEntities);

                int seatEntitiesIndex = 0;

                for (int rowCount = 0; rowCount < maxRowCount; rowCount++) {
                    SeatRow seatRow = new SeatRow();
                    seatRow.rowName = String.valueOf(rowCount);
                    List<Seat> seats = new ArrayList<>();
                    for (int colCount = 0; colCount < maxColCount; colCount++) {
                        Seat seat = new Seat();
                        seat.columnName = String.valueOf(colCount + 1);

                        SeatEntity seatEntity = seatEntities.get(seatEntitiesIndex);
                        if (!seatEntity.getIsSelected()) {
                            seat.status = Seat.STATUS.SELECTABLE;
                        }else{
                            seat.status = Seat.STATUS.UNSELECTABLE;
                        }

                        seat.id = String.valueOf(seatEntity.getId());
                        seats.add(seat);
                        seatEntitiesIndex++;
                    }
                    seatRow.seats = seats;
                    seatRows.add(seatRow);
                }
                return seatRows;
            }

            @Override
            protected void onPostExecute(List<SeatRow> seatMap) {
                SeatView seatView = seatSelectionView.onUpdateSeatView();
                seatView.initSeatView(auditoriumName.toUpperCase(), new SeatImages(ApplicationUtil.context
                        .getResources()), seatMap, 4);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
                seatView.setLayoutParams(params);
                FrameLayout seatViewContainer = getView().onGetSeatViewContainer();
                seatViewContainer.addView(seatView);
                seatSelectionView.onSetSeatView(seatView);
                seatSelectionView.onDismissLoadingDialog();
            }
        }.execute(seatEntities);
    }

    private int getMaxRowNumber(List<SeatEntity> seatEntities) {
        int maxRow = 0;
        for (SeatEntity se : seatEntities) {
            if (se.getRowNumber() > maxRow) {
                maxRow = se.getRowNumber();
            }
        }
        return maxRow;
    }

    private int getMaxColNumber(List<SeatEntity> seatEntities) {
        int maxCol = 0;
        for (SeatEntity se : seatEntities) {
            if (se.getRowNumber() > maxCol) {
                maxCol = se.getColNumber();
            }
        }
        return maxCol;
    }

    private List<SeatRow> querySeatMap() {
        List<SeatRow> seatRows = new ArrayList<>();
        for (int rowCount = 0; rowCount < 10; rowCount++) {
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
}
