package com.jm.newvista.mvp.view;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.WatchlistRecyclerViewAdapter;

public interface WatchlistView extends BaseView {
    WatchlistRecyclerViewAdapter onGetWatchlistRecyclerViewAdapter();

    void onMakeToast(String message);
}
