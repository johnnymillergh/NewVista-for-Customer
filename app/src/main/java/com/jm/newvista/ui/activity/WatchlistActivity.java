package com.jm.newvista.ui.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.WatchlistModel;
import com.jm.newvista.mvp.presenter.WatchlistPresenter;
import com.jm.newvista.mvp.view.WatchlistView;
import com.jm.newvista.ui.adapter.WatchlistRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseActivity;

public class WatchlistActivity
        extends BaseActivity<WatchlistModel, WatchlistView, WatchlistPresenter>
        implements WatchlistView {
    private Toolbar toolbar;
    private RecyclerView watchlistRecyclerView;
    private WatchlistRecyclerViewAdapter watchlistRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        initView();
    }

    @Override
    public WatchlistView createView() {
        return this;
    }

    @Override
    public WatchlistPresenter createPresenter() {
        return new WatchlistPresenter();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        watchlistRecyclerView = findViewById(R.id.watchlistRecyclerView);
        watchlistRecyclerViewAdapter = new WatchlistRecyclerViewAdapter(this);
        watchlistRecyclerView.setAdapter(watchlistRecyclerViewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        watchlistRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
