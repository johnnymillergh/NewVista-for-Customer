package com.jm.newvista.mvp.view;

import android.content.Context;

import com.jm.newvista.mvp.base.BaseView;

import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Johnny on 3/23/2018.
 */

public interface ClearCacheView extends BaseView {
    Context onGetContext();

    void onUpdateCacheSize(String text);

    void onShowPieChart(String[] strings);

    void onDismissProgressBar();

    PieChartView onGetPieChartView();

    void onDeleteCacheDone();
}
