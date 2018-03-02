package com.jm.newvista.mvp.view;

import android.content.Intent;

import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 2/25/2018.
 */

public interface SeatSelectionView extends BaseView {
    Intent onGetIntent();

    void onUpdateToolBar(String movieTitle);

    void onUpdateBasicInfo(MovieScheduleEntity movieScheduleEntity);

    void onUpdateSeatView();
}
