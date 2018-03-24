package com.jm.newvista.mvp.view;

import android.content.Intent;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.MovieScheduleDetailRecyclerViewAdapter;

/**
 * Created by Johnny on 3/24/2018.
 */

public interface MovieScheduleDetailView extends BaseView {
    Intent onGetIntent();

    MovieScheduleDetailRecyclerViewAdapter onGetMovieScheduleDetailRecyclerViewAdapter();

    void onMakeToast(String message);
}
