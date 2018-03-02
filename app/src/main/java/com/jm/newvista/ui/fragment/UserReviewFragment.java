package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.model.UserReviewModel;
import com.jm.newvista.mvp.presenter.UserReviewPresenter;
import com.jm.newvista.mvp.view.UserReviewView;
import com.jm.newvista.ui.adapter.UserReviewRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

import java.util.List;

public class UserReviewFragment extends BaseFragment<UserReviewModel, UserReviewView, UserReviewPresenter>
        implements UserReviewView,
        PopupMenu.OnMenuItemClickListener {
    private UserReviewFragmentListener mListener;
    private String movieTitle;
    private ImageButton sort;
    private RecyclerView userReviewRecyclerView;
    private UserReviewRecyclerViewAdapter userReviewRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    public UserReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_review, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListener.onDisplayRefreshing();
        sort = view.findViewById(R.id.sort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSort(v);
            }
        });

        userReviewRecyclerView = view.findViewById(R.id.userReviewRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        userReviewRecyclerView.setLayoutManager(linearLayoutManager);
        userReviewRecyclerViewAdapter = new UserReviewRecyclerViewAdapter();
        userReviewRecyclerView.setNestedScrollingEnabled(false);
        userReviewRecyclerView.setAdapter(userReviewRecyclerViewAdapter);
        userReviewRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),
                DividerItemDecoration.VERTICAL));
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim
                .animation_layout_fade_in);
        userReviewRecyclerView.setLayoutAnimation(animation);
    }

    private void onClickSort(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.sort_user_review_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newest_first:
                linearLayoutManager.setReverseLayout(true);
                break;
            case R.id.oldest_first:
                linearLayoutManager.setReverseLayout(false);
                break;
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserReviewFragmentListener) {
            mListener = (UserReviewFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public UserReviewView createView() {
        return this;
    }

    @Override
    public UserReviewPresenter createPresenter() {
        return new UserReviewPresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        if (mListener != null) {
            movieTitle = mListener.onGetMovieTitle();
            getPresenter().getAndDisplayUserReview(movieTitle);
        }
    }

    @Override
    public void onSetUserReviewList(List<UserReviewEntity> userReviews) {
        if (mListener != null) {
            mListener.onFinishRefreshing();
            userReviewRecyclerViewAdapter.setUserReviews(userReviews);
            userReviewRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailLoadingUserReview() {
        mListener.onFinishRefreshing();
    }

    public interface UserReviewFragmentListener {
        String onGetMovieTitle();

        void onDisplayRefreshing();

        void onFinishRefreshing();
    }
}
