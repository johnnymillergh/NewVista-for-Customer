package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        implements UserReviewView {

    private UserReviewFragmentListener mListener;
    private String movieTitle;
    private RecyclerView userReviewRecyclerView;
    private UserReviewRecyclerViewAdapter userReviewRecyclerViewAdapter;

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
        userReviewRecyclerView = view.findViewById(R.id.userReviewRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        userReviewRecyclerView.setLayoutManager(linearLayoutManager);
        userReviewRecyclerViewAdapter = new UserReviewRecyclerViewAdapter();
        userReviewRecyclerView.setNestedScrollingEnabled(false);
        userReviewRecyclerView.setAdapter(userReviewRecyclerViewAdapter);
        userReviewRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),
                DividerItemDecoration.VERTICAL));
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
        movieTitle = mListener.onGetMovieTitle();
        Log.v("UserReviewFragment", movieTitle);
        getPresenter().getAndDisplayUserReview(movieTitle);
    }

    @Override
    public void onSetUserReviewList(List<UserReviewEntity> userReviews) {
        userReviewRecyclerViewAdapter.setUserReviews(userReviews);
        userReviewRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNotifyDataChanged() {
        userReviewRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public interface UserReviewFragmentListener {
        String onGetMovieTitle();
    }
}
