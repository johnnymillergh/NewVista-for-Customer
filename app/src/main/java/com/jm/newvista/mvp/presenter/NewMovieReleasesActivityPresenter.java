package com.jm.newvista.mvp.presenter;

import com.jm.newvista.R;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.NewMovieReleasesActivityModel;
import com.jm.newvista.mvp.view.NewMovieReleasesActivityView;
import com.jm.newvista.ui.adapter.SearchResultRecyclerViewAdapter;
import com.jm.newvista.util.ApplicationUtil;

import java.util.List;

/**
 * Created by Johnny on 2/23/2018.
 */

public class NewMovieReleasesActivityPresenter
        extends BasePresenter<NewMovieReleasesActivityModel, NewMovieReleasesActivityView> {
    private NewMovieReleasesActivityModel newMovieReleasesActivityModel;
    private NewMovieReleasesActivityView newMovieReleasesActivityView;

    public NewMovieReleasesActivityPresenter() {
        newMovieReleasesActivityModel = new NewMovieReleasesActivityModel();
        super.BasePresenter(newMovieReleasesActivityModel);
    }

    public void getAndDisplayNewMovies() {
        newMovieReleasesActivityView = getView();

        newMovieReleasesActivityView.onSetToolBarTitle(ApplicationUtil.getContext().getString(R.string
                .new_movie_releases));

        SearchResultRecyclerViewAdapter searchResultRecyclerViewAdapter = newMovieReleasesActivityView
                .onGetSearchResultRecyclerViewAdapter();
        List newMovies = newMovieReleasesActivityModel.getNewMovie();
        searchResultRecyclerViewAdapter.setSearchResultList(newMovies);
        searchResultRecyclerViewAdapter.notifyDataSetChanged();
    }
}
