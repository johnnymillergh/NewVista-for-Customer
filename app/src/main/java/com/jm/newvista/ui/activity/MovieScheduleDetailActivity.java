package com.jm.newvista.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.model.MovieScheduleDetailModel;
import com.jm.newvista.mvp.presenter.MovieScheduleDetailPresenter;
import com.jm.newvista.mvp.view.MovieScheduleDetailView;
import com.jm.newvista.receiver.FinishActivityReceiver;
import com.jm.newvista.ui.adapter.MovieScheduleDetailRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.ui.fragment.TheaterFragment;

public class MovieScheduleDetailActivity
        extends BaseActivity<MovieScheduleDetailModel, MovieScheduleDetailView, MovieScheduleDetailPresenter>
        implements MovieScheduleDetailView,
        TheaterFragment.TheaterFragmentListener {
    private Toolbar toolbar;
    private RecyclerView movieScheduleDetailRecyclerView;
    private MovieScheduleDetailRecyclerViewAdapter movieScheduleDetailRecyclerViewAdapter;

    private FinishActivityReceiver finishActivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_schedule_detail);

        finishActivityReceiver = new FinishActivityReceiver(this);
        registerReceiver(finishActivityReceiver, new IntentFilter("FinishActivity:PaymentDone"));

        initView();
        initFragment();
        getPresenter().getAndDisplayMovieScheduleDetail();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(finishActivityReceiver);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.choose_an_auditorium);
        toolbar.setSubtitle(getIntent().getStringExtra("movieTitle"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Find recycler view
        movieScheduleDetailRecyclerView = findViewById(R.id.movieScheduleDetailRecyclerView);
        movieScheduleDetailRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        movieScheduleDetailRecyclerView.setLayoutManager(linearLayoutManager);

        // Set adapter
        movieScheduleDetailRecyclerViewAdapter = new MovieScheduleDetailRecyclerViewAdapter();
        movieScheduleDetailRecyclerView.setAdapter(movieScheduleDetailRecyclerViewAdapter);

        // Add item decoration
        movieScheduleDetailRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        // Add item animation
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim
                .animation_layout_fade_in);
        movieScheduleDetailRecyclerView.setLayoutAnimation(animation);
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.theaterContainer, new TheaterFragment()).commit();
    }

    @Override
    public MovieScheduleDetailView createView() {
        return this;
    }

    @Override
    public MovieScheduleDetailPresenter createPresenter() {
        return new MovieScheduleDetailPresenter();
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public MovieScheduleDetailRecyclerViewAdapter onGetMovieScheduleDetailRecyclerViewAdapter() {
        return movieScheduleDetailRecyclerViewAdapter;
    }

    @Override
    public void onMakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public MovieScheduleEntity onGetMovieScheduleInfo() {
        Intent intent = getIntent();
        String theaterName = intent.getStringExtra("theaterName");
        String location = intent.getStringExtra("location");
        String movieTitle = intent.getStringExtra("movieTitle");
        MovieScheduleEntity entity = new MovieScheduleEntity();
        entity.setTheaterName(theaterName);
        entity.setLocation(location);
        entity.setMovieTitle(movieTitle);
        return entity;
    }
}
