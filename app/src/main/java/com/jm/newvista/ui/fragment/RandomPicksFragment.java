package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jm.newvista.R;
import com.jm.newvista.ui.adapter.RandomPicksRecyclerViewAdapter;

import java.util.List;

public class RandomPicksFragment extends Fragment {
    private List<MotionEvent> randomPicks;
    private RandomPicksFragmentListener mListener;

    private RecyclerView randomPicksRecyclerView;
    private RandomPicksRecyclerViewAdapter randomPicksRecyclerViewAdapter;
    private Button more;

    public RandomPicksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_random_picks, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        randomPicksRecyclerView = view.findViewById(R.id.randomPicksRecyclerView);
        more = view.findViewById(R.id.more);

        randomPicksRecyclerViewAdapter = new RandomPicksRecyclerViewAdapter(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        randomPicksRecyclerView.setLayoutManager(layoutManager);
        randomPicksRecyclerView.setAdapter(randomPicksRecyclerViewAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RandomPicksFragmentListener) {
            mListener = (RandomPicksFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface RandomPicksFragmentListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
