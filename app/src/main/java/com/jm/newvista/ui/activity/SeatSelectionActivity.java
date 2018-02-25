package com.jm.newvista.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.jm.newvista.R;
import com.qfdqc.views.seattable.SeatTable;

import java.util.ArrayList;
import java.util.List;

import io.github.lh911002.seatview.seat.ISeatListener;
import io.github.lh911002.seatview.seat.Seat;
import io.github.lh911002.seatview.seat.SeatImages;
import io.github.lh911002.seatview.seat.SeatRow;
import io.github.lh911002.seatview.seat.SeatView;

public class SeatSelectionActivity extends AppCompatActivity implements ISeatListener {
    private Toolbar toolbar;
    private TextView movieTitle;
    private TextView showtime;
    private SeatTable seatTable;
    private RecyclerView selectionResultRecyclerView;
    private Button confirm;
    private SeatView seatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        movieTitle = findViewById(R.id.movieTitle);
        showtime = findViewById(R.id.showtime);
//        seatTable.findViewById(R.id.seatTable);
        selectionResultRecyclerView = findViewById(R.id.selectionResultRecyclerView);
        confirm = findViewById(R.id.confirm);
        seatView = findViewById(R.id.seatView);
        seatView.setSeatClickListener(this);
        seatView.initSeatView("Github", new SeatImages(getResources()), querySeatMap());
    }

    public void onClickConfirm(View view) {

    }

    private List<SeatRow> querySeatMap() {
        List<SeatRow> seatRows = new ArrayList<>();
        for (int rowCount = 0; rowCount < 26; rowCount++) {
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
                if (rowCount == 3 && colCount == 8) {
                    seat.status = Seat.STATUS.SELECTED;
                }
                if (colCount == 5 & rowCount != 10) {
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
        Toast.makeText(this, "已选" + seatView.getSubmitSeats().size() + "个座位: " + canceledSeat, Toast
                .LENGTH_SHORT).show();
    }

    @Override
    public void lockSeat(Seat selectedSeat) {
        Toast.makeText(this, "已选" + seatView.getSubmitSeats().size() + "个座位: " + selectedSeat, Toast
                .LENGTH_SHORT).show();
    }
}
