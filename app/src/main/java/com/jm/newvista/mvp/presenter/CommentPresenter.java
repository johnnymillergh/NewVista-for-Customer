package com.jm.newvista.mvp.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.jm.newvista.R;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.CommentModel;
import com.jm.newvista.mvp.view.CommentView;
import com.jm.newvista.ui.adapter.CommentRecyclerViewAdapter;

import java.util.List;

public class CommentPresenter extends BasePresenter<CommentModel, CommentView> {
    private CommentModel commentModel;
    private CommentView commentView;

    public CommentPresenter() {
        commentModel = new CommentModel();
        super.BasePresenter(commentModel);
    }

    public void getAndDisplayComment() {
        commentView = getView();
        commentModel.getUserReview(new CommentModel.GetUserReviewListener() {
            @Override
            public void onSuccess(List<UserReviewEntity> userReviews) {
                CommentRecyclerViewAdapter adapter = commentView.onGetCommentRecyclerViewAdapter();
                adapter.setUserReviews(userReviews);

                RecyclerView recyclerView = commentView.onGetCommentRecyclerView();
                Context context = recyclerView.getContext();

                LayoutAnimationController controller =
                        AnimationUtils.loadLayoutAnimation(context, R.anim.animation_layout_from_bottom_to_top);

                recyclerView.setLayoutAnimation(controller);
                recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();
            }

            @Override
            public void onNullResult() {
                commentView.onMakeToast("Null Result");
            }

            @Override
            public void onFailure(String errorMessage) {
                commentView.onMakeToast(errorMessage);
            }
        });
    }
}
