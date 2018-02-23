package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.RandomPicksModel;
import com.jm.newvista.mvp.view.RandomPicksView;

/**
 * Created by Johnny on 2/23/2018.
 */

public class RandomPicksPresenter extends BasePresenter<RandomPicksModel, RandomPicksView> {
    private RandomPicksModel randomPicksModel;
    private RandomPicksView randomPicksView;

    public RandomPicksPresenter() {
        randomPicksModel = new RandomPicksModel();
        super.BasePresenter(randomPicksModel);
    }

    public void getAndDisplayRandomPicks() {

    }
}
