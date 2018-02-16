package com.jm.newvista.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 1/31/2018.
 */

public abstract class BaseFragment<M extends BaseModel, V extends BaseView, P extends BasePresenter<M, V>> extends
        Fragment {
    private P presenter;
    private V view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (view == null) {
            view = createView();
        }
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter != null && view != null) {
            presenter.attachView(view);
        }
        notifyFinishAttachingView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null && view != null) {
            presenter.detachView();
        }
    }

    public abstract V createView();

    public abstract P createPresenter();

    public abstract void notifyFinishAttachingView();

    public P getPresenter() {
        return presenter;
    }

    public V getViewOfMVP() {
        return view;
    }
}
