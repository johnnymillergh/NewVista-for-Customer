package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jm.newvista.R;
import com.jm.newvista.ui.adapter.TopMovieViewPagerAdapter;
import com.jm.newvista.ui.myclass.MyViewPager;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopMovieFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewPager topMovieViewPager;
    //    private IndefinitePagerIndicator pagerIndicator;
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
        MyViewPager viewPager = (MyViewPager) view.findViewById(R.id.topMovieViewPager);
        TopMovieViewPagerAdapter pagerAdapter = new TopMovieViewPagerAdapter(getContext());
        viewPager.setAdapter(pagerAdapter);
        IndefinitePagerIndicator pagerIndicator = (IndefinitePagerIndicator) view.findViewById(R.id.topMovieIndicator);
        pagerIndicator.attachToViewPager(viewPager);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
