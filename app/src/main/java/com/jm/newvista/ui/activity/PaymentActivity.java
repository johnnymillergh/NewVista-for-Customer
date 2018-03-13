package com.jm.newvista.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.PaymentModel;
import com.jm.newvista.mvp.presenter.PaymentPresenter;
import com.jm.newvista.mvp.view.PaymentView;
import com.jm.newvista.ui.base.BaseActivity;

public class PaymentActivity
        extends BaseActivity<PaymentModel, PaymentView, PaymentPresenter>
        implements PaymentView {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener((v) -> {
            finish();
        });


    }

    @Override
    public PaymentView createView() {
        return this;
    }

    @Override
    public PaymentPresenter createPresenter() {
        return new PaymentPresenter();
    }
}
