package com.jm.newvista.ui.activity;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.jm.newvista.R;
import com.jm.newvista.mvp.model.TicketDetailModel;
import com.jm.newvista.mvp.presenter.TicketDetailPresenter;
import com.jm.newvista.mvp.view.TicketDetailView;
import com.jm.newvista.ui.base.BaseActivity;
import com.journeyapps.barcodescanner.BarcodeEncoder;

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
    private TextView theaterName2;
    private TextView theaterLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        initView();
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        theaterName = findViewById(R.id.theaterName);
        movieTitle = findViewById(R.id.movieTitle);
        showtime = findViewById(R.id.showtime);
        seatLocation = findViewById(R.id.seatLocation);
        qrCode = findViewById(R.id.qrCode);
        orderId = findViewById(R.id.orderId);
        totalPrice = findViewById(R.id.totalPrice);
        ticketAmount = findViewById(R.id.ticketAmount);
        theaterName2 = findViewById(R.id.theaterName2);
        theaterLocation = findViewById(R.id.theaterLocation);

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap("Johnny Miller", BarcodeFormat.QR_CODE, 400, 400);
            qrCode.setImageBitmap(bitmap);
        } catch(Exception e) {

        }
    }
}
