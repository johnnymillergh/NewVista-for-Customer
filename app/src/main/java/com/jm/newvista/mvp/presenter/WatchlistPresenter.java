package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.WatchlistModel;
import com.jm.newvista.mvp.view.WatchlistView;

public class WatchlistPresenter extends BasePresenter<WatchlistModel, WatchlistView> {
    private WatchlistModel watchlistModel;
    private WatchlistView watchlistView;

    public WatchlistPresenter() {
        watchlistModel = new WatchlistModel();
        super.BasePresenter(watchlistModel);
    }
}
