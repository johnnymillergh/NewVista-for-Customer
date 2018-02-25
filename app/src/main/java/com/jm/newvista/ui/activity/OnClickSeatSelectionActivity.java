package com.jm.newvista.ui.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jm.newvista.R;

import java.util.ArrayList;
import java.util.List;

import io.github.lh911002.seatview.seat.OnClickSeatListener;
import io.github.lh911002.seatview.seat.Seat;
import io.github.lh911002.seatview.seat.SeatImages;
import io.github.lh911002.seatview.seat.SeatRow;
import io.github.lh911002.seatview.seat.SeatView;

public class OnClickSeatSelectionActivity extends AppCompatActivity implements OnClickSeatListener {
    private Toolbar toolbar;
    private TextView movieTitle;
    private TextView showtime;
    private LinearLayout selectionResultContainer;
    private TextView seatSelection;
    private Button confirm;
    private SeatView seatView;

    private List<Seat> seats = new ArrayList<>();

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
        selectionResultContainer = findViewById(R.id.selectionResultContainer);
        seatSelection = findViewById(R.id.seatSelection);
        seatSelection.setVerticalFadingEdgeEnabled(true);
        seatSelection.setMovementMethod(ScrollingMovementMethod.getInstance());
        confirm = findViewById(R.id.confirm);
        seatView = findViewById(R.id.seatView);
        seatView.setOnClickSeatListener(this);
        seatView.initSeatView("Auditorium 1".toUpperCase(), new SeatImages(getResources()), querySeatMap(), 4);
    }

    public void onClickConfirm(View view) {

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

    @SuppressLint("SetTextI18n")
    @Override
    public void releaseSeat(Seat canceledSeat) {
        seats.remove(canceledSeat);
        if (seats.size() == 0) seatSelection.setText(getString(R.string.seat_not_selected));
        else seatSelection.setText(getString(R.string.selected_seats) + seats);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void lockSeat(Seat selectedSeat) {
        seats.add(selectedSeat);
        seatSelection.setText(getString(R.string.selected_seats) + seats);
    }

    @Override
    public void onExceedSMaxSelectionCount(int maxSelectionCount) {
        Toast.makeText(this, getString(R.string.seats_warning) + maxSelectionCount, Toast.LENGTH_SHORT).show();
    }
}
