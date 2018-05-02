package com.jm.newvista.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.jm.newvista.R;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.mvp.model.TicketDetailModel;
import com.jm.newvista.mvp.presenter.TicketDetailPresenter;
import com.jm.newvista.mvp.view.TicketDetailView;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.util.SeatLocationUtil;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TicketDetailActivity
        extends BaseActivity<TicketDetailModel, TicketDetailView, TicketDetailPresenter>
        implements TicketDetailView {
    private Toolbar toolbar;
    private TextView theaterName;
    private TextView movieTitle;
    private TextView showtime;
    private TextView seatLocation;
    private ImageView qrCode;
    private TextView orderId;
    private TextView totalPrice;
    private TextView ticketAmount;
    private TextView orderDatetime;
    private TextView theaterName2;
    private TextView theaterLocation;

    private CustomerOrderEntity currentOrderEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        initView();
        getPresenter().updateView();
    }

    @Override
    public TicketDetailView createView() {
        return this;
    }

    @Override
    public TicketDetailPresenter createPresenter() {
        return new TicketDetailPresenter();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener((v) -> finish());

        theaterName = findViewById(R.id.theaterName);
        movieTitle = findViewById(R.id.movieTitle);
        showtime = findViewById(R.id.showtime);
        seatLocation = findViewById(R.id.seatLocation);
        qrCode = findViewById(R.id.qrCode);
        orderId = findViewById(R.id.orderId);
        totalPrice = findViewById(R.id.totalPrice);
        ticketAmount = findViewById(R.id.ticketAmount);
        orderDatetime = findViewById(R.id.orderDatetime);
        theaterName2 = findViewById(R.id.theaterName2);
        theaterLocation = findViewById(R.id.theaterLocation);

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap("Johnny Miller", BarcodeFormat.QR_CODE, 400, 400);
            qrCode.setImageBitmap(bitmap);
        } catch (Exception e) {

        }
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public void onUpdateView(CustomerOrderEntity orderEntity) {
        currentOrderEntity = orderEntity;

        theaterName.setText(orderEntity.getTheaterName());
        movieTitle.setText(orderEntity.getMovieTitle());

        Date date = orderEntity.getShowtime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:ss aa MMM d, yyyy", Locale.ENGLISH);
        String dateStr = simpleDateFormat.format(date);
        showtime.setText(dateStr);

        String sl = orderEntity.getSeatLocation();
        List<SeatLocationUtil.SeatLocation> seatLocation2 = SeatLocationUtil.parse(sl);
        seatLocation.setText("Row " + seatLocation2.get(0).getRow() + ", Col " + seatLocation2.get(0).getCol());
        orderId.setText(String.valueOf(orderEntity.getId()));

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String totalPriceStr = decimalFormat.format(orderEntity.getTotalPrice());
        totalPrice.setText(totalPriceStr);

        ticketAmount.setText(String.valueOf(orderEntity.getTicketAmount()));

        date = orderEntity.getOrderDatetime();
        dateStr = simpleDateFormat.format(date);
        orderDatetime.setText(dateStr);

        theaterName2.setText(orderEntity.getTheaterName());
        theaterLocation.setText(orderEntity.getTheaterLocation());

        if (!orderEntity.getIsPaid()) {
            Glide.with(this).load(R.drawable.order_unpaid).into(qrCode);
            qrCode.setOnClickListener(v -> {
                Intent intent = new Intent(TicketDetailActivity.this, PaymentActivity.class);

                intent.putExtra("orderEntity", new GsonBuilder().disableHtmlEscaping().create().toJson
                        (currentOrderEntity));
                startActivity(intent);
            });
        }
    }

    @Override
    public CustomerOrderEntity onGetCurrentOrderEntity() {
        return currentOrderEntity;
    }

    @Override
    public void onUpdateQRCode(String plainText) {
        if (!currentOrderEntity.getIsUsed()) {
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(plainText, BarcodeFormat.QR_CODE, 400, 400);
                qrCode.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
