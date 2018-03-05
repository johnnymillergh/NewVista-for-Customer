package com.jm.newvista.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.NewMovieReleasesActivityModel;
import com.jm.newvista.mvp.presenter.NewMovieReleasesActivityPresenter;
import com.jm.newvista.mvp.view.NewMovieReleasesActivityView;
import com.jm.newvista.ui.adapter.SearchResultRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseActivity;

public class NewMovieReleasesActivity
        extends BaseActivity<NewMovieReleasesActivityModel, NewMovieReleasesActivityView,
        NewMovieReleasesActivityPresenter>
        implements NewMovieReleasesActivityView {
    private Toolbar toolbar;
    private RecyclerView newMovieReleasesRecyclerView;
    private SearchResultRecyclerViewAdapter searchResultRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movie_releases);
        initView();
        getPresenter().getAndDisplayNewMovies();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newMovieReleasesRecyclerView = findViewById(R.id.newMovieReleasesRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newMovieReleasesRecyclerView.setLayoutManager(linearLayoutManager);
        searchResultRecyclerViewAdapter = new SearchResultRecyclerViewAdapter(this);
        newMovieReleasesRecyclerView.setAdapter(searchResultRecyclerViewAdapter);
        newMovieReleasesRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public NewMovieReleasesActivityView createView() {
        return this;
    }

    @Override
    public NewMovieReleasesActivityPresenter createPresenter() {
        return new NewMovieReleasesActivityPresenter();
    }

    @Override
    public void onSetToolBarTitle(String toolBarTitle) {
        toolbar.setTitle(toolBarTitle);
    }

    @Override
    public SearchResultRecyclerViewAdapter onGetSearchResultRecyclerViewAdapter() {
        return searchResultRecyclerViewAdapter;
    }
}
