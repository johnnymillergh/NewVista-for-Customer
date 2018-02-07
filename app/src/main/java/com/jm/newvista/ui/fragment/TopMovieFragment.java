package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.TopMovieModel;
import com.jm.newvista.mvp.presenter.TopMoviePresenter;
import com.jm.newvista.mvp.view.TopMovieView;
import com.jm.newvista.ui.adapter.TopMovieViewPagerAdapter;
import com.jm.newvista.ui.base.BaseFragment;
import com.jm.newvista.ui.myview.MyViewPager;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

public class TopMovieFragment extends BaseFragment<TopMovieModel, TopMovieView, TopMoviePresenter> implements
        TopMovieView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private MyViewPager topMovieViewPager;
    private TopMovieViewPagerAdapter topMovieViewPagerAdapter;

    private TopMovieCallbackListener topMovieCallbackListener;

    public TopMovieFragment() {
        // Required empty public constructor
    }

    public static TopMovieFragment newInstance() {
        TopMovieFragment fragment = new TopMovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ViewPager
        View view = inflater.inflate(R.layout.fragment_top_movie, container, false);
        topMovieViewPager = (MyViewPager) view.findViewById(R.id.topMovieViewPager);
        topMovieViewPagerAdapter = new TopMovieViewPagerAdapter(getContext());
        topMovieViewPager.setAdapter(topMovieViewPagerAdapter);
        topMovieViewPager.setOffscreenPageLimit(4);
        PageIndicatorView pageIndicatorView = (PageIndicatorView) view.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(topMovieViewPager);
        pageIndicatorView.setAnimationType(AnimationType.WORM);

        // RecyclerView
//        View view = inflater.inflate(R.layout.fragment_top_movie, container, false);
//        RecyclerView recyclerView = view.findViewById(R.id.topMovieRecyclerView);
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
//        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);
//        TopMovieRecyclerViewAdapter topMovieRecyclerViewAdapter = new TopMovieRecyclerViewAdapter();
//        recyclerView.setAdapter(topMovieRecyclerViewAdapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (topMovieCallbackListener != null) {
            topMovieCallbackListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TopMovieCallbackListener) {
            topMovieCallbackListener = (TopMovieCallbackListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement TopMovieCallbackListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        topMovieCallbackListener = null;
    }

    @Override
    public TopMovieView createView() {
        return this;
    }

    @Override
    public TopMoviePresenter createPresenter() {
        return new TopMoviePresenter();
    }

    @Override
    public void notifyFinishAttaching() {
        getPresenter().getTopMovieAndDisplay();
    }

    @Override
    public void onDisplayTopMovieTitle() {

    }

    @Override
    public void onDisplayTopMoviePoster() {

    }

    @Override
    public TopMovieViewPagerAdapter getViewPagerAdapter() {
        return topMovieViewPagerAdapter;
    }

    @Override
    public MyViewPager getTopMovieViewPager() {
        return topMovieViewPager;
    }

    public interface TopMovieCallbackListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
