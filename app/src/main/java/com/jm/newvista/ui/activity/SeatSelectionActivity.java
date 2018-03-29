package com.jm.newvista.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.model.SeatSelectionModel;
import com.jm.newvista.mvp.presenter.SeatSelectionPresenter;
import com.jm.newvista.mvp.view.SeatSelectionView;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.ui.dialog.LoadingAlertDialog;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.github.lh911002.seatview.seat.OnClickSeatListener;
import io.github.lh911002.seatview.seat.Seat;
import io.github.lh911002.seatview.seat.SeatView;

public class SeatSelectionActivity extends BaseActivity<SeatSelectionModel, SeatSelectionView, SeatSelectionPresenter>
        implements SeatSelectionView, OnClickSeatListener {
    private Toolbar toolbar;
    private TextView movieTitle;
    private TextView showtime;
    private TextView seatSelection;
    private Button confirm;
    private FrameLayout seatViewContainer;
    private LoadingAlertDialog loadingAlertDialog;

    private SeatView seatView;

    private List<Seat> selectedSeats = new ArrayList<>();

    private MovieScheduleEntity currentMovieSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        initView();
        getPresenter().updateToolBar();
        getPresenter().getAndDisplayMovieSchedule();
        getPresenter().getAndDisplaySeat();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        movieTitle = findViewById(R.id.movieTitle);
        showtime = findViewById(R.id.showtime);
        seatSelection = findViewById(R.id.seatSelection);
        seatSelection.setVerticalFadingEdgeEnabled(true);
        seatSelection.setMovementMethod(ScrollingMovementMethod.getInstance());
        confirm = findViewById(R.id.confirm);
        seatViewContainer = findViewById(R.id.seatViewContainer);
    }

    public void onClickConfirm(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        getPresenter().takeOrder();
//        if (currentMovieSchedule != null && selectedSeats.size() != 0) {
//            intent.putExtra("movieTitle", currentMovieSchedule.getMovieTitle());
//
//            // Format datetime
//            Date date = currentMovieSchedule.getShowtime();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:m:ss aa MMM d, yyyy", Locale.ENGLISH);
//            String dateStr = simpleDateFormat.format(date);
//            intent.putExtra("showtime", dateStr);
//
//            intent.putExtra("seat", selectedSeats.toString());
//
//            // Format total price
//            DecimalFormat decimalFormat = new DecimalFormat(".00");
//            String totalPrice = decimalFormat.format(currentMovieSchedule.getPrice() * selectedSeats.size());
//            intent.putExtra("totalPrice", totalPrice);
//
//            intent.putExtra("movieScheduleId", currentMovieSchedule.getId());
//            startActivity(intent);
//        }
    }

    @Override
    public SeatSelectionView createView() {
        return this;
    }

    @Override
    public SeatSelectionPresenter createPresenter() {
        return new SeatSelectionPresenter();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void releaseSeat(Seat canceledSeat) {
        selectedSeats.remove(canceledSeat);
        if (selectedSeats.size() == 0) seatSelection.setText(getString(R.string.seat_not_selected));
        else seatSelection.setText(getString(R.string.selected_seats) + selectedSeats);

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String total = decimalFormat.format(currentMovieSchedule.getPrice() * selectedSeats.size());
        confirm.setText(getString(R.string.total) + total + getString(R.string.confirm2));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void lockSeat(Seat selectedSeat) {
        selectedSeats.add(selectedSeat);
        seatSelection.setText(getString(R.string.selected_seats) + selectedSeats);

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String total = decimalFormat.format(currentMovieSchedule.getPrice() * selectedSeats.size());
        confirm.setText(getString(R.string.total) + total + getString(R.string.confirm2));
    }

    @Override
    public void onExceedMaxSelectionCount(int maxSelectionCount) {
        Toast.makeText(this, getString(R.string.seats_warning) + maxSelectionCount, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public void onUpdateToolBar(String movieTitle) {
        toolbar.setSubtitle(movieTitle);
    }

    @Override
    public void onUpdateBasicInfo(MovieScheduleEntity movieScheduleEntity) {
        currentMovieSchedule = movieScheduleEntity;

        movieTitle.setText(movieScheduleEntity.getMovieTitle());

        // Format datetime
        Date date = movieScheduleEntity.getShowtime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:m:ss aa MMM d, yyyy", Locale.ENGLISH);
        String dateStr = simpleDateFormat.format(date);

        showtime.setText(dateStr);
    }

    @Override
    public SeatView onUpdateSeatView() {
        return new SeatView(this);
    }

    @Override
    public FrameLayout onGetSeatViewContainer() {
        return seatViewContainer;
    }

    @Override
    public void onDisplayLoadingDialog() {
        loadingAlertDialog = new LoadingAlertDialog(this);
        loadingAlertDialog.show();
    }

    @Override
    public void onDismissLoadingDialog() {
        if (loadingAlertDialog != null) loadingAlertDialog.dismiss();
    }

    @Override
    public void onSetSeatView(SeatView seatView) {
        this.seatView = seatView;
        this.seatView.setOnClickSeatListener(this);
    }

    @Override
    public void onMakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<Seat> onGetSelectedSeats() {
        return selectedSeats;
    }

    @Override
    public MovieScheduleEntity onGetCurrentMovieSchedule() {
        return currentMovieSchedule;
    }

    @Override
    public void onStartPaymentActivity(CustomerOrderEntity orderEntity) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("orderId", orderEntity.getId());

        intent.putExtra("movieTitle", orderEntity.getMovieTitle());

        // Format datetime
        Date date = currentMovieSchedule.getShowtime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:m:ss aa MMM d, yyyy", Locale.ENGLISH);
        String dateStr = simpleDateFormat.format(date);
        intent.putExtra("showtime", orderEntity.getShowtime());

        intent.putExtra("seat", orderEntity.getSeatLocation());

        // Format total price
        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String totalPrice = decimalFormat.format(orderEntity.getTotalPrice());
        intent.putExtra("totalPrice", totalPrice);

        intent.putExtra("movieScheduleId", orderEntity.getMovieScheduleId());
        startActivity(intent);
    }
}
