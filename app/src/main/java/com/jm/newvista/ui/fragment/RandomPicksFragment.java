package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.content.Intent;
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

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.model.RandomPicksModel;
import com.jm.newvista.mvp.presenter.RandomPicksPresenter;
import com.jm.newvista.mvp.view.RandomPicksView;
import com.jm.newvista.ui.activity.NowInTheatersActivity;
import com.jm.newvista.ui.activity.RandomPicksActivity;
import com.jm.newvista.ui.adapter.RandomPicksRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

import java.util.List;

public class RandomPicksFragment
        extends BaseFragment<RandomPicksModel, RandomPicksView, RandomPicksPresenter>
        implements RandomPicksView {
    private List<MovieEntity> randomPicks;
    private RandomPicksFragmentListener mListener;

    private RecyclerView randomPicksRecyclerView;
    private RandomPicksRecyclerViewAdapter randomPicksRecyclerViewAdapter;
    private Button more;

    public RandomPicksFragment() {
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
        View view = inflater.inflate(R.layout.fragment_random_picks, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        randomPicksRecyclerView = view.findViewById(R.id.randomPicksRecyclerView);
        more = view.findViewById(R.id.more);
        more.setOnClickListener(v -> onClickMore(v));

        randomPicksRecyclerViewAdapter = new RandomPicksRecyclerViewAdapter(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        randomPicksRecyclerView.setLayoutManager(layoutManager);
        randomPicksRecyclerView.setAdapter(randomPicksRecyclerViewAdapter);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.animation_layout_fade_in);
        randomPicksRecyclerView.setLayoutAnimation(animation);
    }

    private void onClickMore(View v) {
        Intent intent = new Intent(getActivity(), RandomPicksActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RandomPicksFragmentListener) {
            mListener = (RandomPicksFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement RandomPicksFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFinishPreparedRandomPicks(List<MovieEntity> randomPicks) {
        this.randomPicks = randomPicks;
        randomPicksRecyclerViewAdapter.setRandomPicks(randomPicks);
        randomPicksRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public RandomPicksView createView() {
        return this;
    }

    @Override
    public RandomPicksPresenter createPresenter() {
        return new RandomPicksPresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        getPresenter().getAndDisplayRandomPicks();
    }

    public interface RandomPicksFragmentListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
