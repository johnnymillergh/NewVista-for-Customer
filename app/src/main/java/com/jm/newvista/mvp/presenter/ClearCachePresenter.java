package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.ClearCacheModel;
import com.jm.newvista.mvp.view.ClearCacheView;

import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Johnny on 3/23/2018.
 */

public class ClearCachePresenter extends BasePresenter<ClearCacheModel, ClearCacheView> {
    private ClearCacheModel clearCacheModel;
    private ClearCacheView clearCacheView;

    public ClearCachePresenter() {
        this.clearCacheModel = new ClearCacheModel();
        super.BasePresenter(clearCacheModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void calculateCacheSize() {
        clearCacheView = getView();
        new AsyncTask<Void, Void, String[]>() {
            @Override
            protected String[] doInBackground(Void... voids) {
                String cacheSize = clearCacheModel.getCacheSize(clearCacheView.onGetContext());
                String internalMemorySize = clearCacheModel.getInternalMemorySize(clearCacheView.onGetContext());
                String[] array = new String[2];
                array[0] = cacheSize;
                array[1] = internalMemorySize;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return array;
            }

            @Override
            protected void onPostExecute(String[] s) {
                clearCacheView.onUpdateCacheSize(s[0]);
                clearCacheView.onShowPieChart(s);
            }
        }.execute();
    }

    public void updatePieChartView(String[] strings) {
        String[] cache = strings[0].split(" ");
        String[] internal = strings[1].split(" ");

        float cacheSize = Float.valueOf(cache[0]);
        float internalSize = Float.valueOf(internal[0]) * 1024;

        PieChartView pieChartView = clearCacheView.onGetPieChartView();
        PieChartData data = pieChartView.getPieChartData();

        List<SliceValue> values = data.getValues();

        values.get(0).setTarget(cacheSize);
        values.get(1).setTarget(internalSize);
        pieChartView.startDataAnimation();
    }

    public void clearCache() {
        clearCacheModel.deleteCache(clearCacheView.onGetContext(), () -> clearCacheView.onDeleteCacheDone());
    }
}
