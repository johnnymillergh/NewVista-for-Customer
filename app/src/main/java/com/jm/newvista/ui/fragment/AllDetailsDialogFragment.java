package com.jm.newvista.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;

public class AllDetailsDialogFragment extends DialogFragment {
    private TextView genre;
    private TextView director;
    private TextView stars;
    private TextView country;
    private TextView language;
    private TextView releaseDate;
    private TextView filmingLocation;
    private TextView runtime;
    private TextView aspectRatio;

    private AllDetailsDialogFragmentCallbackListener mListener;

    public void show(FragmentManager manager) {
        show(manager, "DescriptionDialogFragment");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_all_details_dialog, null);
        MovieEntity movieEntity = mListener.onGetMovie();
        initView(view);
        updateView(movieEntity);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view).setPositiveButton(getString(R.string.back), null);
        return builder.create();
    }

    private void updateView(MovieEntity movieEntity) {
        genre.setText(movieEntity.getGenre());
        director.setText(movieEntity.getDirector());
        stars.setText(movieEntity.getStars());
        country.setText(movieEntity.getCountry());
        language.setText(movieEntity.getLanguage());
        releaseDate.setText(movieEntity.getReleaseDate());
        filmingLocation.setText(movieEntity.getFilmingLocation());
        runtime.setText(movieEntity.getRuntime());
        aspectRatio.setText(movieEntity.getAspectRatio());
    }

    private void initView(View rootView) {
        genre = rootView.findViewById(R.id.genre);
        director = rootView.findViewById(R.id.director);
        stars = rootView.findViewById(R.id.stars);
        country = rootView.findViewById(R.id.country);
        language = rootView.findViewById(R.id.language);
        releaseDate = rootView.findViewById(R.id.releaseDate);
        filmingLocation = rootView.findViewById(R.id.filmingLocation);
        runtime = rootView.findViewById(R.id.runtime);
        aspectRatio = rootView.findViewById(R.id.aspectRatio);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AllDetailsDialogFragmentCallbackListener) {
            mListener = (AllDetailsDialogFragmentCallbackListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AllDetailsDialogFragmentCallbackListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface AllDetailsDialogFragmentCallbackListener {
        MovieEntity onGetMovie();
    }
}
