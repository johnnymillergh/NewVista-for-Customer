package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jm.newvista.R;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.model.RateMovieModel;
import com.jm.newvista.mvp.presenter.RateMoviePresenter;
import com.jm.newvista.mvp.view.RateMovieView;
import com.jm.newvista.ui.base.BaseFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class RateMovieFragment extends BaseFragment<RateMovieModel, RateMovieView, RateMoviePresenter>
        implements RateMovieView {
    private String movieTitle;

    private RateMovieFragmentListener mListener;

    private CircleImageView avatar;
    private RatingBar ratingBar;
    private TextInputEditText title;
    private TextInputEditText text;
    private RadioButton yes;
    private RadioButton no;
    private Button submit;

    public RateMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rate_movie, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        avatar = view.findViewById(R.id.avatar);
        ratingBar = view.findViewById(R.id.ratingBar);
        title = view.findViewById(R.id.title);
        text = view.findViewById(R.id.text);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);
        submit = view.findViewById(R.id.submit);

        title.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.title) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });

        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.text) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubmit(v);
            }
        });
    }

    private void onClickSubmit(View v) {
        getPresenter().submitUserReview();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RateMovieFragmentListener) {
            mListener = (RateMovieFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement RateMovieFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public RateMovieView createView() {
        return this;
    }

    @Override
    public RateMoviePresenter createPresenter() {
        return new RateMoviePresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        getPresenter().displayUserAvatar();
    }

    @Override
    public void onUpdateAvatar(UserEntity userEntity) {
        Glide.with(this).load(userEntity.getAvatar()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                avatar.setImageDrawable(resource);
            }
        });
    }

    @Override
    public String getMovieTitle() {
        movieTitle = mListener.onGetMovieTitle();
        return movieTitle;
    }

    @Override
    public float getScores() {
        return ratingBar.getRating();
    }

    @Override
    public String getTitle() {
        return title.getText().toString();
    }

    @Override
    public String getText() {
        return text.getText().toString();
    }

    @Override
    public boolean getIsSpoilers() {
        if (yes.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearReview() {
        title.setText("");
        text.setText("");
        ratingBar.setRating(1f);
    }

    public interface RateMovieFragmentListener {
        String onGetMovieTitle();
    }
}
