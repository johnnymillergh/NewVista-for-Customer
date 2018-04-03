package com.jm.newvista.ui.fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.TopSellingModel;
import com.jm.newvista.mvp.presenter.TopSellingPresenter;
import com.jm.newvista.mvp.view.TopSellingView;
import com.jm.newvista.ui.adapter.TopSellingRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

public class TopSellingFragment
        extends BaseFragment<TopSellingModel, TopSellingView, TopSellingPresenter>
        implements TopSellingView {
    private TopSellingFragmentListener mListener;

    private RecyclerView topSellingRecyclerView;
    private TopSellingRecyclerViewAdapter topSellingRecyclerViewAdapter;
    private Button more;

    public TopSellingFragment() {
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
        View view = inflater.inflate(R.layout.fragment_top_selling, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        more = view.findViewById(R.id.more);
        more.setOnClickListener(this::onClickMore);

        topSellingRecyclerView = view.findViewById(R.id.topSellingRecyclerView);
        topSellingRecyclerViewAdapter = new TopSellingRecyclerViewAdapter(getActivity());
        topSellingRecyclerView.setAdapter(topSellingRecyclerViewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        topSellingRecyclerView.setLayoutManager(layoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim
                .animation_layout_fade_in);
        topSellingRecyclerView.setLayoutAnimation(animation);
    }

    private void onClickMore(View view) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TopSellingFragmentListener) {
            mListener = (TopSellingFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement TopSellingFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public TopSellingView createView() {
        return this;
    }

    @Override
    public TopSellingPresenter createPresenter() {
        return new TopSellingPresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        getPresenter().getAndDisplayTopSelling();
    }

    @Override
    public TopSellingRecyclerViewAdapter onGetTopSellingRecyclerViewAdapter() {
        return topSellingRecyclerViewAdapter;
    }

    @Override
    public void onMakeToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public interface TopSellingFragmentListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
