package com.jm.newvista.mvp.view;

import android.content.Intent;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 2/11/2018.
 */

public interface MovieView extends BaseView {
    void onDisplayRefreshing();

    void onFinishRefreshing();

    Intent onGetIntent();

    void onUpdateMovieInformation(MovieEntity movieEntity);

    void onDisplayDescriptionDialog();

    void onDisplayAllDetailsDialog();
}
