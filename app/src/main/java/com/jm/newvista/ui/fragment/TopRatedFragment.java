package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.TopRatedModel;
import com.jm.newvista.mvp.presenter.TopRatedPresenter;
import com.jm.newvista.mvp.view.TopRatedView;
import com.jm.newvista.ui.base.BaseFragment;

public class TopRatedFragment
        extends BaseFragment<TopRatedModel, TopRatedView, TopRatedPresenter>
        implements TopRatedView {
    private RecyclerView topRatedRecyclerView;
    private Button more;
    private TopRatedFragmentListener mListener;

    public TopRatedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        topRatedRecyclerView = view.findViewById(R.id.topRatedRecyclerView);
        more = view.findViewById(R.id.more);
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
        return null;
    }

    @Override
    public TopRatedPresenter createPresenter() {
        return null;
    }

    @Override
    public void notifyFinishAttachingView() {

    }

    public interface TopRatedFragmentListener {
        void onFragmentInteraction(Uri uri);
    }
}
