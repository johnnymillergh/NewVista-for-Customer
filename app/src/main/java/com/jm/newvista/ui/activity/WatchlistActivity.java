package com.jm.newvista.ui.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.model.WatchlistModel;
import com.jm.newvista.mvp.presenter.WatchlistPresenter;
import com.jm.newvista.mvp.view.WatchlistView;
import com.jm.newvista.ui.adapter.WatchlistRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseActivity;

public class WatchlistActivity
        extends BaseActivity<WatchlistModel, WatchlistView, WatchlistPresenter>
        implements WatchlistView{
    private Toolbar toolbar;
    private RecyclerView watchlistRecyclerView;
    private WatchlistRecyclerViewAdapter watchlistRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        initView();

        getPresenter().getAndDisplayWatchlist();
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
        watchlistRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public WatchlistRecyclerViewAdapter onGetWatchlistRecyclerViewAdapter() {
        return watchlistRecyclerViewAdapter;
    }

    @Override
    public void onMakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveWatchlistItem(MovieEntity movieEntity) {
        getPresenter().removeWatchlistItem(movieEntity);
    }
}
