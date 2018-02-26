package com.jm.newvista.mvp.view;

import android.content.Intent;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.MovieScheduleRecyclerViewAdapter;

/**
 * Created by Johnny on 2/26/2018.
 */

public interface MovieScheduleView extends BaseView {
    Intent onGetIntent();

    void onSetToolBarTitle(String toolBarTitle);

    MovieScheduleRecyclerViewAdapter onGetMovieScheduleRecyclerViewAdapter();

    void onNullResult();

    void onGetMovieScheduleFailure();
}
