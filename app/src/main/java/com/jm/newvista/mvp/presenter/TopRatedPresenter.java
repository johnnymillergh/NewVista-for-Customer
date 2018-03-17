package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopRatedModel;
import com.jm.newvista.mvp.view.TopRatedView;

/**
 * Created by Johnny on 3/17/2018.
 */

public class TopRatedPresenter extends BasePresenter<TopRatedModel, TopRatedView> {
    private TopRatedModel topRatedModel;
    private TopRatedView topRatedView;

    public TopRatedPresenter() {
        topRatedModel = new TopRatedModel();
        super.BasePresenter(topRatedModel);
    }
}
