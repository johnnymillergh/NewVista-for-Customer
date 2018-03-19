package com.jm.newvista.mvp.view;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.TopRatedRecyclerViewAdapter;

/**
 * Created by Johnny on 3/17/2018.
 */

public interface TopRatedView extends BaseView {
    TopRatedRecyclerViewAdapter onGetTopRatedRecyclerViewAdapter();
}
