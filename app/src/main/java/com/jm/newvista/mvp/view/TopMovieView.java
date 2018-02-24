package com.jm.newvista.mvp.view;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.TopMovieViewPagerAdapter;
import com.jm.newvista.ui.mine.view.MyViewPager;

/**
 * Created by Johnny on 2/2/2018.
 */

public interface TopMovieView extends BaseView {
    void onDisplayTopMovieTitle();

    void onDisplayTopMoviePoster();

    TopMovieViewPagerAdapter getViewPagerAdapter();

    MyViewPager getTopMovieViewPager();
}
