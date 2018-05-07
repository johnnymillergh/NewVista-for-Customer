package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.model.TheaterModel;
import com.jm.newvista.mvp.presenter.TheaterPresenter;
import com.jm.newvista.mvp.view.TheaterView;
import com.jm.newvista.ui.base.BaseFragment;
import com.jm.newvista.util.NetworkUtil;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class TheaterFragment extends BaseFragment<TheaterModel, TheaterView, TheaterPresenter> implements TheaterView {
    private TheaterFragmentListener mListener;
    private MovieScheduleEntity currentMovieScheduleEntity;
    private TextView theaterName;
    private TextView location;
    private ImageView poster;

    public TheaterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theater, container, false);
        theaterName = view.findViewById(R.id.theaterName);
        location = view.findViewById(R.id.location);
        poster = view.findViewById(R.id.poster);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TheaterFragmentListener) {
            mListener = (TheaterFragmentListener) context;
            currentMovieScheduleEntity = mListener.onGetMovieScheduleInfo();
        } else {
            throw new RuntimeException(context.toString() + " must implement TheaterFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public TheaterView createView() {
        return this;
    }

    @Override
    public TheaterPresenter createPresenter() {
        return new TheaterPresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        getPresenter().updateView();
    }

    @Override
    public void onUpdateView() {
        if (currentMovieScheduleEntity != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(300);
            alphaAnimation.setStartOffset(600);

            theaterName.setText(currentMovieScheduleEntity.getTheaterName());
            theaterName.startAnimation(alphaAnimation);

            location.setText(currentMovieScheduleEntity.getLocation());
            location.startAnimation(alphaAnimation);

            Glide.with(getContext()).load(NetworkUtil.GET_MOVIE_POSTER_URL + currentMovieScheduleEntity.getMovieTitle())
                    .transition(withCrossFade()).into(poster);
        }
    }

    public interface TheaterFragmentListener {
        MovieScheduleEntity onGetMovieScheduleInfo();
    }
}
