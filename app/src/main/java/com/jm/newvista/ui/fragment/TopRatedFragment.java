package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.TopRatedModel;
import com.jm.newvista.mvp.presenter.TopRatedPresenter;
import com.jm.newvista.mvp.view.TopRatedView;
import com.jm.newvista.ui.adapter.TopRatedRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

public class TopRatedFragment
        extends BaseFragment<TopRatedModel, TopRatedView, TopRatedPresenter>
        implements TopRatedView {
    private RecyclerView topRatedRecyclerView;
    private TopRatedRecyclerViewAdapter topRatedRecyclerViewAdapter;
    private Button more;
    private TopRatedFragmentListener mListener;

    public TopRatedFragment() {
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
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        more = view.findViewById(R.id.more);
        more.setOnClickListener(this::onClickMore);

        topRatedRecyclerView = view.findViewById(R.id.topRatedRecyclerView);
        topRatedRecyclerViewAdapter = new TopRatedRecyclerViewAdapter(getActivity());
        topRatedRecyclerView.setAdapter(topRatedRecyclerViewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        topRatedRecyclerView.setLayoutManager(layoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim
                .animation_layout_fade_in);
        topRatedRecyclerView.setLayoutAnimation(animation);
    }

    private void onClickMore(View v) {
        Toast.makeText(getContext(), "onClickMore", Toast.LENGTH_SHORT).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TopRatedFragmentListener) {
            mListener = (TopRatedFragmentListener) context;
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
    public TopRatedView createView() {
        return this;
    }

    @Override
    public TopRatedPresenter createPresenter() {
        return new TopRatedPresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        getPresenter().getAndDisplayTopRated();
    }

    @Override
    public TopRatedRecyclerViewAdapter onGetTopRatedRecyclerViewAdapter() {
        return topRatedRecyclerViewAdapter;
    }

    public interface TopRatedFragmentListener {
        void onFragmentInteraction(Uri uri);
    }
}
