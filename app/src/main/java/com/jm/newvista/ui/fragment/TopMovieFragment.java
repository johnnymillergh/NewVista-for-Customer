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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopMovieFragment extends BaseFragment<TopMovieModel, TopMovieView, TopMoviePresenter> implements
        TopMovieView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MyViewPager myViewPager;
    private TopMovieViewPagerAdapter topMovieViewPagerAdapter;

    private OnFragmentInteractionListener mListener;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_movie, container, false);
        myViewPager = (MyViewPager) view.findViewById(R.id.topMovieViewPager);
        topMovieViewPagerAdapter = new TopMovieViewPagerAdapter(getContext());
        myViewPager.setAdapter(topMovieViewPagerAdapter);
        PageIndicatorView pageIndicatorView = (PageIndicatorView) view.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(myViewPager);
        pageIndicatorView.setAnimationType(AnimationType.WORM);
        return view;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public TopMovieView createView() {
        return this;
    }

    @Override
    public TopMoviePresenter createPresenter() {
        TopMoviePresenter topMoviePresenter = new TopMoviePresenter();
        topMoviePresenter.getTopMovieAndDisplay();
        return topMoviePresenter;
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
    public MyViewPager getMyViewPager() {
        return myViewPager;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
