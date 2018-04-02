package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.content.res.Resources;
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
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.GenreModel;
import com.jm.newvista.mvp.presenter.GenrePresenter;
import com.jm.newvista.mvp.view.GenreView;
import com.jm.newvista.ui.adapter.GenreRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

import java.util.Arrays;
import java.util.List;


public class GenreFragment
        extends BaseFragment<GenreModel, GenreView, GenrePresenter>
        implements GenreView,
        GenreRecyclerViewAdapter.OnChipClickListener {
    private GenreFragmentCallbackListener genreFragmentCallbackListener;

    public GenreFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        RecyclerView genreRecyclerView = view.findViewById(R.id.genreRecyclerView);
        GenreRecyclerViewAdapter genreRecyclerViewAdapter = new GenreRecyclerViewAdapter(this);

        List<String> genres = genreRecyclerViewAdapter.getGenres();
        String[] genresArray = getResources().getStringArray(R.array.genres_array);
        genres.addAll(Arrays.asList(genresArray));

        genreRecyclerView.setAdapter(genreRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        genreRecyclerView.setLayoutManager(linearLayoutManager);
        genreRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GenreFragmentCallbackListener) {
            genreFragmentCallbackListener = (GenreFragmentCallbackListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GenreFragmentCallbackListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        genreFragmentCallbackListener = null;
    }

    @Override
    public GenreView createView() {
        return this;
    }

    @Override
    public GenrePresenter createPresenter() {
        return new GenrePresenter();
    }

    @Override
    public void notifyFinishAttachingView() {

    }

    @Override
    public void onChipClick(View v, String chipText) {
        getPresenter().clickChipView(chipText);
    }

    public interface GenreFragmentCallbackListener {
        void onFragmentInteraction(Uri uri);
    }
}
