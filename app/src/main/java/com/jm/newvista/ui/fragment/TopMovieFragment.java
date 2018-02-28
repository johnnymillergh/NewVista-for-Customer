package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.TopMovieModel;
import com.jm.newvista.mvp.presenter.TopMoviePresenter;
import com.jm.newvista.mvp.view.TopMovieView;
import com.jm.newvista.ui.adapter.TopMovieViewPagerAdapter;
import com.jm.newvista.ui.base.BaseFragment;
import com.jm.newvista.ui.mine.view.MyViewPager;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

public class TopMovieFragment extends BaseFragment<TopMovieModel, TopMovieView, TopMoviePresenter> implements
        TopMovieView {
    private MyViewPager topMovieViewPager;
    private TopMovieViewPagerAdapter topMovieViewPagerAdapter;

    private TopMovieFragmentListener topMovieFragmentListener;

    public TopMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        } else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ViewPager
        View view = inflater.inflate(R.layout.fragment_top_movie, container, false);
        topMovieViewPager = (MyViewPager) view.findViewById(R.id.topMovieViewPager);
        topMovieViewPagerAdapter = new TopMovieViewPagerAdapter();
        topMovieViewPager.setAdapter(topMovieViewPagerAdapter);
        topMovieViewPager.setOffscreenPageLimit(4);
        PageIndicatorView pageIndicatorView = (PageIndicatorView) view.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(topMovieViewPager);
        pageIndicatorView.setAnimationType(AnimationType.WORM);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TopMovieFragmentListener) {
            topMovieFragmentListener = (TopMovieFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement TopMovieFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        topMovieFragmentListener = null;
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
    public void notifyFinishAttachingView() {
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

    public interface TopMovieFragmentListener {
        void onFragmentInteraction(Uri uri);
    }
}
