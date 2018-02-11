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
import android.widget.Button;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.model.NewMovieReleasesModel;
import com.jm.newvista.mvp.presenter.NewMovieReleasesPresenter;
import com.jm.newvista.mvp.view.NewMovieReleasesView;
import com.jm.newvista.ui.activity.MainActivity;
import com.jm.newvista.ui.adapter.NewMovieReleasesRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

import java.util.List;

public class NewMovieReleasesFragment
        extends BaseFragment<NewMovieReleasesModel, NewMovieReleasesView, NewMovieReleasesPresenter>
        implements NewMovieReleasesView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView newMovieReleasesRecyclerView;
    private NewMovieReleasesRecyclerViewAdapter newMovieReleasesRecyclerViewAdapter;
    private Button more;

    private NewMovieReleasesFragmentCallbackListener mListener;

    public NewMovieReleasesFragment() {
        // Required empty public constructor
    }

    public static NewMovieReleasesFragment newInstance(String param1, String param2) {
        NewMovieReleasesFragment fragment = new NewMovieReleasesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter) {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_from_bottom_to_top);
        } else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_from_bottom_to_top);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_movie_releases, container, false);
        newMovieReleasesRecyclerView = (RecyclerView) view.findViewById(R.id.newMovieReleasesRecyclerView);
        more = (Button) view.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMoreClick(v);
            }
        });
        newMovieReleasesRecyclerViewAdapter = new NewMovieReleasesRecyclerViewAdapter(mListener.onGetActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        newMovieReleasesRecyclerView.setLayoutManager(layoutManager);
        newMovieReleasesRecyclerView.setAdapter(newMovieReleasesRecyclerViewAdapter);
        newMovieReleasesRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    public void onMoreClick(View view) {
        Toast.makeText(getContext(), "More click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewMovieReleasesFragmentCallbackListener) {
            mListener = (NewMovieReleasesFragmentCallbackListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GenreFragmentCallbackListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public NewMovieReleasesView createView() {
        return this;
    }

    @Override
    public NewMovieReleasesPresenter createPresenter() {
        return new NewMovieReleasesPresenter();
    }

    @Override
    public void notifyFinishAttaching() {
        getPresenter().getNewMovie();
    }

    @Override
    public void onFinishPreparingNewMovie(List<MovieEntity> newMovies) {
        newMovieReleasesRecyclerViewAdapter.setNewMovies(newMovies);
        newMovieReleasesRecyclerViewAdapter.notifyDataSetChanged();
    }

    public interface NewMovieReleasesFragmentCallbackListener {
        MainActivity onGetActivity();
    }
}
