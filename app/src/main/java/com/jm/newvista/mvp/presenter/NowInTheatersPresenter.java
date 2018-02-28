package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.NowInTheatersModel;
import com.jm.newvista.mvp.view.NowInTheatersView;

/**
 * Created by Johnny on 2/28/2018.
 */

public class NowInTheatersPresenter extends BasePresenter<NowInTheatersModel, NowInTheatersView> {
    private NowInTheatersModel nowInTheatersModel;
    private NowInTheatersPresenter nowInTheatersPresenter;

    public NowInTheatersPresenter() {
        nowInTheatersModel = new NowInTheatersModel();
        super.BasePresenter(nowInTheatersModel);
    }
}
