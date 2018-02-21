package com.jm.newvista.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.SearchResultModel;
import com.jm.newvista.mvp.presenter.SearchResultPresenter;
import com.jm.newvista.mvp.view.SearchResultView;
import com.jm.newvista.ui.adapter.GenreRecyclerViewAdapter;
import com.jm.newvista.ui.adapter.SearchResultRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseActivity;

public class SearchResultActivity extends BaseActivity<SearchResultModel, SearchResultView, SearchResultPresenter>
        implements SearchResultView {
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private RecyclerView searchResultRecyclerView;
    private SearchResultRecyclerViewAdapter searchResultRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initView();
        getPresenter().getAndDisplayResult();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        searchResultRecyclerView = findViewById(R.id.searchResultRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchResultRecyclerView.setLayoutManager(linearLayoutManager);
        searchResultRecyclerViewAdapter = new SearchResultRecyclerViewAdapter();
        searchResultRecyclerView.setAdapter(searchResultRecyclerViewAdapter);
        searchResultRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public SearchResultView createView() {
        return this;
    }

    @Override
    public SearchResultPresenter createPresenter() {
        return new SearchResultPresenter();
    }

    @Override
    public void onSetToolBarTitle(String toolBarTitle) {
        toolbar.setTitle(toolBarTitle);
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public SearchResultRecyclerViewAdapter onGetSearchResultRecyclerViewAdapter() {
        return searchResultRecyclerViewAdapter;
    }

    @Override
    public void onDisplayLoadingDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onDismissLoadingDialog() {
        if (progressDialog != null) progressDialog.dismiss();
    }
}
