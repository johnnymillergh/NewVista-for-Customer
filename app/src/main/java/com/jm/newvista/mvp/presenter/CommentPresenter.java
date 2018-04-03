package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.CommentModel;
import com.jm.newvista.mvp.view.CommentView;

public class CommentPresenter extends BasePresenter<CommentModel, CommentView> {
    private CommentModel commentModel;
    private CommentView commentView;

    public CommentPresenter() {
        commentModel = new CommentModel();
        super.BasePresenter(commentModel);
    }
}
