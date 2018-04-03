package com.jm.newvista.mvp.view;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.TopSellingRecyclerViewAdapter;

public interface TopSellingView extends BaseView {
    TopSellingRecyclerViewAdapter onGetTopSellingRecyclerViewAdapter();

    void onMakeToast(String message);
}
