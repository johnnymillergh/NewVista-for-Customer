package com.jm.newvista.mvp.view;

import android.content.Intent;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.SearchResultRecyclerViewAdapter;

/**
 * Created by Johnny on 2/20/2018.
 */

public interface SearchResultView extends BaseView {
    void onSetToolBarTitle(String toolBarTitle);

    Intent onGetIntent();

    SearchResultRecyclerViewAdapter onGetSearchResultRecyclerViewAdapter();

    void onDisplayLoadingDialog(String message);

    void onDismissLoadingDialog();
}
