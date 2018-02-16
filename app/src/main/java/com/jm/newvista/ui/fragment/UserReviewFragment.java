package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.UserReviewModel;
import com.jm.newvista.mvp.presenter.UserReviewPresenter;
import com.jm.newvista.mvp.view.UserReviewView;
import com.jm.newvista.ui.base.BaseFragment;

public class UserReviewFragment extends BaseFragment<UserReviewModel,UserReviewView,UserReviewPresenter>
        implements UserReviewView {

    private UserReviewFragmentListener mListener;

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
        return view;
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

    }

    public interface UserReviewFragmentListener {
        String onGetMovieTitle();
    }
}
