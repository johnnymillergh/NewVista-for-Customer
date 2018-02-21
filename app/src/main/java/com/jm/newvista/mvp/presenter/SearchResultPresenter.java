package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.SearchResultModel;
import com.jm.newvista.mvp.view.SearchResultView;
import com.jm.newvista.ui.adapter.SearchResultRecyclerViewAdapter;
import com.jm.newvista.util.ApplicationUtil;

import java.util.List;

/**
 * Created by Johnny on 2/20/2018.
 */

public class SearchResultPresenter extends BasePresenter<SearchResultModel, SearchResultView> {
    private SearchResultModel searchResultModel;
    private SearchResultView searchResultView;

    public SearchResultPresenter() {
        searchResultModel = new SearchResultModel();
        super.BasePresenter(searchResultModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void getAndDisplayResult() {
        searchResultView = getView();
        Intent intent = searchResultView.onGetIntent();
        String from = intent.getStringExtra("from");
        switch (from) {
            case "SearchBar:Title":
                searchResultView.onDisplayLoadingDialog(ApplicationUtil.getContext().getString(R.string.loading));
                final String title = intent.getStringExtra("title");
                new AsyncTask<Void, Void, List<MovieEntity>>() {
                    @Override
                    protected List doInBackground(Void... voids) {
                        MovieEntity movieEntity = new MovieEntity();
                        movieEntity.setTitle(title);
                        return searchResultModel.getSearchResultByTitle(movieEntity);
                    }

                    @Override
                    protected void onPostExecute(List<MovieEntity> list) {
                        SearchResultRecyclerViewAdapter searchResultRecyclerViewAdapter = searchResultView
                                .onGetSearchResultRecyclerViewAdapter();
                        searchResultRecyclerViewAdapter.setSearchResultList(list);
                        searchResultRecyclerViewAdapter.notifyDataSetChanged();
                        searchResultView.onSetToolBarTitle(title);
                        searchResultView.onDismissLoadingDialog();
                    }
                }.execute();
                break;
            case "SearchBar:Star":
                break;
            case "Genre":
                break;
            default:
        }
    }
}
