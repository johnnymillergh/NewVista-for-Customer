package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.RateMovieModel;
import com.jm.newvista.mvp.presenter.RateMoviePresenter;
import com.jm.newvista.mvp.view.RateMovieView;
import com.jm.newvista.ui.base.BaseFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class RateMovieFragment extends BaseFragment<RateMovieModel,RateMovieView,RateMoviePresenter>
        implements RateMovieView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

    public static RateMovieFragment newInstance(String param1, String param2) {
        RateMovieFragment fragment = new RateMovieFragment();
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubmit(v);
            }
        });
    }

    private void onClickSubmit(View v) {

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

    }

    public interface RateMovieFragmentListener {
        void onFragmentInteraction(Uri uri);
    }
}
