package com.jm.newvista.mvp.view;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.SearchResultRecyclerViewAdapter;

/**
 * Created by Johnny on 2/23/2018.
 */

public interface NewMovieReleasesActivityView extends BaseView {
    void onSetToolBarTitle(String toolBarTitle);

    SearchResultRecyclerViewAdapter onGetSearchResultRecyclerViewAdapter();
}
