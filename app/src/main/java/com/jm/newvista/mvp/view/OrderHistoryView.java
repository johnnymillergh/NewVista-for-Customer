package com.jm.newvista.mvp.view;

import android.support.v7.widget.RecyclerView;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.OrderHistoryRecyclerViewAdapter;

public interface OrderHistoryView extends BaseView {
    OrderHistoryRecyclerViewAdapter onGetOrderHistoryRecyclerViewAdapter();

    RecyclerView onGetOrderHistoryRecyclerView();

    void onMakeToast(String message);
}
