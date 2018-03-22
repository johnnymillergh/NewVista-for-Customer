package com.jm.newvista.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.model.UserReviewModel;
import com.jm.newvista.mvp.presenter.UserReviewPresenter;
import com.jm.newvista.mvp.view.UserReviewView;
import com.jm.newvista.ui.adapter.UserReviewRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class UserReviewFragment extends BaseFragment<UserReviewModel, UserReviewView, UserReviewPresenter>
        implements UserReviewView,
        PopupMenu.OnMenuItemClickListener {
    private UserReviewFragmentListener mListener;
    private String movieTitle;
    private TextView averageScore;
    private ColumnChartView chart;
    private ColumnChartData data;
    private TextView userReviewsCount;
    private ImageButton sort;
    private RecyclerView userReviewRecyclerView;
    private UserReviewRecyclerViewAdapter userReviewRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;

    public UserReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_review, container, false);
        initView(view);
        initChart();
        return view;
    }

    private void initView(View view) {
        mListener.onDisplayRefreshing();
        averageScore = view.findViewById(R.id.averageScore);
        chart = view.findViewById(R.id.chart);
        userReviewsCount = view.findViewById(R.id.userReviewsCount);

        sort = view.findViewById(R.id.sort);
        sort.setOnClickListener(v -> onClickSort(v));

        userReviewRecyclerView = view.findViewById(R.id.userReviewRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        userReviewRecyclerView.setLayoutManager(linearLayoutManager);
        userReviewRecyclerViewAdapter = new UserReviewRecyclerViewAdapter();
        userReviewRecyclerView.setNestedScrollingEnabled(false);
        userReviewRecyclerView.setAdapter(userReviewRecyclerViewAdapter);
        userReviewRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),
                DividerItemDecoration.VERTICAL));
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim
                .animation_layout_fade_in);
        userReviewRecyclerView.setLayoutAnimation(animation);
    }

    private void initChart() {
        int numSubColumns = 1;
        int numColumns = 11;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; i++) {
            values = new ArrayList<>();
            for (int j = 0; j < numSubColumns; j++) {
                values.add(new SubcolumnValue(0, ChartUtils.pickColor()));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }
        data = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Score");
                axisY.setName("User");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        chart.setColumnChartData(data);
        chart.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueDeselected() {

            }

            @Override
            public void onValueSelected(int i, int i1, SubcolumnValue subcolumnValue) {
                Toast.makeText(getContext(), "User count: " + (int) subcolumnValue.getValue(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        chart.startDataAnimation();
    }

    private void onClickSort(View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.fragment_user_review_sort, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newestFirst:
                linearLayoutManager.setReverseLayout(true);
                break;
            case R.id.oldestFirst:
                linearLayoutManager.setReverseLayout(false);
                break;
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserReviewFragmentListener) {
            mListener = (UserReviewFragmentListener) context;
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
    public UserReviewView createView() {
        return this;
    }

    @Override
    public UserReviewPresenter createPresenter() {
        return new UserReviewPresenter();
    }

    @Override
    public void notifyFinishAttachingView() {
        if (mListener != null) {
            movieTitle = mListener.onGetMovieTitle();
            getPresenter().getAndDisplayUserReview(movieTitle);
        }
    }

    @Override
    public void onSetUserReviewList(List<UserReviewEntity> userReviews) {
        if (mListener != null) {
            mListener.onFinishRefreshing();
            userReviewRecyclerViewAdapter.setUserReviews(userReviews);
            userReviewRecyclerViewAdapter.notifyDataSetChanged();
            getPresenter().displayUserReviewStatistics(data, userReviewRecyclerViewAdapter.getUserReviews());
        }
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailLoadingUserReview() {
        mListener.onFinishRefreshing();
    }

    @Override
    public void onSetUserReviewsCount(int count) {
        userReviewsCount.setText(String.valueOf(count));
    }

    @Override
    public void onUpdateChart(float score) {
        chart.startDataAnimation();

        DecimalFormat decimalFormat = new DecimalFormat(".0");
        String scoreFormatted = decimalFormat.format(score);
        averageScore.setText(scoreFormatted);
    }

    public interface UserReviewFragmentListener {
        String onGetMovieTitle();

        void onDisplayRefreshing();

        void onFinishRefreshing();
    }
}
