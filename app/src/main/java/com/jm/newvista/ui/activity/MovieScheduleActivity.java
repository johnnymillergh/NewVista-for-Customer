package com.jm.newvista.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.MovieScheduleModel;
import com.jm.newvista.mvp.presenter.MovieSchedulePresenter;
import com.jm.newvista.mvp.view.MovieScheduleView;
import com.jm.newvista.ui.base.BaseActivity;

public class MovieScheduleActivity
        extends BaseActivity<MovieScheduleModel, MovieScheduleView, MovieSchedulePresenter>
        implements MovieScheduleView {
    private Toolbar toolbar;
    private RecyclerView movieScheduleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_schedule);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        movieScheduleRecyclerView = findViewById(R.id.movieScheduleRecyclerView);
    }

    @Override
    public MovieScheduleView createView() {
        return this;
    }

    @Override
    public MovieSchedulePresenter createPresenter() {
        return new MovieSchedulePresenter();
    }
}
