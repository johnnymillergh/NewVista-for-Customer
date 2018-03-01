package com.jm.newvista.mvp.view;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.NowInTheatersRecyclerViewAdapter;

/**
 * Created by Johnny on 2/28/2018.
 */

public interface NowInTheatersView extends BaseView {
    NowInTheatersRecyclerViewAdapter onGetNowInTheatersRecyclerViewAdapter();
}
