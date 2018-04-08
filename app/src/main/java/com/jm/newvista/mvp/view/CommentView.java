package com.jm.newvista.mvp.view;

import android.support.v7.widget.RecyclerView;

import com.jm.newvista.mvp.base.BaseView;
import com.jm.newvista.ui.adapter.CommentRecyclerViewAdapter;

public interface CommentView extends BaseView {
    CommentRecyclerViewAdapter onGetCommentRecyclerViewAdapter();

    RecyclerView onGetCommentRecyclerView();

    void onMakeToast(String message);
}
