package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TopSellingModel;
import com.jm.newvista.mvp.view.TopSellingView;

public class TopSellingPresenter extends BasePresenter<TopSellingModel, TopSellingView> {
    private TopSellingModel topSellingModel;
    private TopSellingView topSellingView;

    public TopSellingPresenter() {
        topSellingModel = new TopSellingModel();
        super.BasePresenter(topSellingModel);
    }
}
