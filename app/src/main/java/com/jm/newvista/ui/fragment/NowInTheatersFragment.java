package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.NowInTheatersModel;
import com.jm.newvista.mvp.presenter.NowInTheatersPresenter;
import com.jm.newvista.mvp.view.NowInTheatersView;
import com.jm.newvista.ui.activity.NewMovieReleasesActivity;
import com.jm.newvista.ui.activity.NowInTheatersActivity;
import com.jm.newvista.ui.adapter.NowInTheatersRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

public class NowInTheatersFragment
        extends BaseFragment<NowInTheatersModel, NowInTheatersView, NowInTheatersPresenter>
        implements NowInTheatersView {
    private NowInTheatersFragmentListener mListener;

    private Button more;
    private RecyclerView nowInTheatersRecyclerView;
    private NowInTheatersRecyclerViewAdapter nowInTheatersRecyclerViewAdapter;

    public NowInTheatersFragment() {
        // Required empty public constructor
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_from_right_to_left);
        } else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_in_theaters, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        more = view.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMore(v);
            }
        });
        nowInTheatersRecyclerView = view.findViewById(R.id.nowInTheatersRecyclerView);
        nowInTheatersRecyclerViewAdapter = new NowInTheatersRecyclerViewAdapter(getActivity());
        nowInTheatersRecyclerView.setAdapter(nowInTheatersRecyclerViewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        nowInTheatersRecyclerView.setLayoutManager(layoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.animation_layout_fade_in);
        nowInTheatersRecyclerView.setLayoutAnimation(animation);
    }

    private void onClickMore(View v) {
        Intent intent = new Intent(getActivity(), NowInTheatersActivity.class);
        startActivity(intent);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NowInTheatersFragmentListener) {
            mListener = (NowInTheatersFragmentListener) context;
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
    public NowInTheatersRecyclerViewAdapter onGetNowInTheatersRecyclerViewAdapter() {
        return nowInTheatersRecyclerViewAdapter;
    }

    @Override
    public NowInTheatersView createView() {
        return this;
    }

    @Override
    public NowInTheatersPresenter createPresenter() {
        return new NowInTheatersPresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        getPresenter().getAndDisplayMoviesInTheaters();
    }

    public interface NowInTheatersFragmentListener {
        void onFragmentInteraction(Uri uri);
    }
}
