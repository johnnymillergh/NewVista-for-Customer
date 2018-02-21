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

import com.jm.newvista.R;
import com.jm.newvista.ui.adapter.GenreRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;


public class GenreFragment extends Fragment {
    private GenreFragmentCallbackListener genreFragmentCallbackListener;

    public GenreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        RecyclerView genreRecyclerView = (RecyclerView) view.findViewById(R.id.genreRecyclerView);
        GenreRecyclerViewAdapter genreRecyclerViewAdapter = new GenreRecyclerViewAdapter();

        List<String> genres = genreRecyclerViewAdapter.getGenres();
        String[] genresArray = getResources().getStringArray(R.array.genres_array);
        genres.addAll(Arrays.asList(genresArray));

        genreRecyclerView.setAdapter(genreRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        genreRecyclerView.setLayoutManager(new RecyclerView.LayoutManager() {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                        .WRAP_CONTENT);
            }
        });
        genreRecyclerView.setLayoutManager(linearLayoutManager);
        genreRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (genreFragmentCallbackListener != null) {
            genreFragmentCallbackListener.onFragmentInteraction(uri);
        }
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

    public interface GenreFragmentCallbackListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
