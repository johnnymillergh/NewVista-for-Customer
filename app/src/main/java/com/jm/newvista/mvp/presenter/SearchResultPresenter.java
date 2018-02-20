package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.SearchResultModel;
import com.jm.newvista.mvp.view.SearchResultView;

/**
 * Created by Johnny on 2/20/2018.
 */

public class SearchResultPresenter extends BasePresenter<SearchResultModel, SearchResultView> {
    private SearchResultModel searchResultModel;
    private SearchResultView searchResultView;

    public SearchResultPresenter() {
        searchResultModel = new SearchResultModel();
        super.BasePresenter(searchResultModel);
    }
}
