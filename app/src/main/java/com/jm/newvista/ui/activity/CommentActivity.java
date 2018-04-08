package com.jm.newvista.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.CommentModel;
import com.jm.newvista.mvp.presenter.CommentPresenter;
import com.jm.newvista.mvp.view.CommentView;
import com.jm.newvista.ui.adapter.CommentRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseActivity;

public class CommentActivity
        extends BaseActivity<CommentModel, CommentView, CommentPresenter>
        implements CommentView {
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView commentRecyclerView;
    private CommentRecyclerViewAdapter commentRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        initView();
    }

    @Override
    public CommentView createView() {
        return this;
    }

    @Override
    public CommentPresenter createPresenter() {
        return new CommentPresenter();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);

        commentRecyclerViewAdapter = new CommentRecyclerViewAdapter();
        commentRecyclerView.setAdapter(commentRecyclerViewAdapter);

        linearLayoutManager = new LinearLayoutManager(this);
        commentRecyclerView.setLayoutManager(linearLayoutManager);

        commentRecyclerView.setNestedScrollingEnabled(false);
    }

    public void onClickSort(View view) {

    }
}
