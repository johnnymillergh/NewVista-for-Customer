package com.jm.newvista.ui.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.ClearCacheModel;
import com.jm.newvista.mvp.presenter.ClearCachePresenter;
import com.jm.newvista.mvp.view.ClearCacheView;
import com.jm.newvista.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class ClearCacheActivity
        extends BaseActivity<ClearCacheModel, ClearCacheView, ClearCachePresenter>
        implements ClearCacheView {
    private Toolbar toolbar;
    private TextView cacheSize;
    private PieChartView pieChartView;
    private PieChartData data;
    private Button clear;
    private ProgressBar progressBar;
    private TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_cache);
        initView();
        generateData();
        getPresenter().calculateCacheSize();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        cacheSize = findViewById(R.id.cacheSize);
        pieChartView = findViewById(R.id.pieChartView);
        clear = findViewById(R.id.clear);
        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.loading);
    }

    private void generateData() {
        int numValues = 2;

        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue(1, ChartUtils.pickColor());
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasCenterCircle(true);
        data.setSlicesSpacing(24);

        // Get roboto-italic font.
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");

        // Get font size from dimens.xml and convert it to sp(library uses sp values).
        data.setCenterText1("Disk Usage");
        data.setCenterText1Typeface(typeface);
        data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
        data.setCenterText2("Charts");
        data.setCenterText2Typeface(typeface);
        data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));

        pieChartView.setPieChartData(data);
    }

    public void onClickClear(View view) {
        getPresenter().clearCache();
    }

    @Override
    public ClearCacheView createView() {
        return this;
    }

    @Override
    public ClearCachePresenter createPresenter() {
        return new ClearCachePresenter();
    }

    @Override
    public Context onGetContext() {
        return this.getBaseContext();
    }

    @Override
    public void onUpdateCacheSize(String text) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(600);

        cacheSize.setText(text);
        cacheSize.startAnimation(alphaAnimation);

        onDismissProgressBar();
    }

    @Override
    public void onShowPieChart(String[] strings) {
        pieChartView.setVisibility(View.VISIBLE);
        getPresenter().updatePieChartView(strings);
    }

    @Override
    public void onDismissProgressBar() {
        progressBar.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public PieChartView onGetPieChartView() {
        return pieChartView;
    }

    @Override
    public void onDeleteCacheDone() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(600);

        cacheSize.setText("0.0 MB");
        cacheSize.startAnimation(alphaAnimation);

        List<SliceValue> values = data.getValues();
        float cacheSize = values.get(0).getValue();
        float internalSize = values.get(1).getValue();
        internalSize += cacheSize;
        values.get(0).setTarget(0);
        values.get(1).setTarget(internalSize);
        pieChartView.startDataAnimation();
    }
}
