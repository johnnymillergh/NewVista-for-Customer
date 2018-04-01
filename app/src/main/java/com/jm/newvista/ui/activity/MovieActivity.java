package com.jm.newvista.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.model.MovieModel;
import com.jm.newvista.mvp.presenter.MoviePresenter;
import com.jm.newvista.mvp.view.MovieView;
import com.jm.newvista.receiver.FinishActivityReceiver;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.ui.fragment.AllDetailsDialogFragment;
import com.jm.newvista.ui.fragment.DescriptionDialogFragment;
import com.jm.newvista.ui.fragment.RateMovieFragment;
import com.jm.newvista.ui.fragment.UserReviewFragment;
import com.jm.newvista.util.NetworkUtil;

import java.lang.reflect.Method;

public class MovieActivity
        extends BaseActivity<MovieModel, MovieView, MoviePresenter>
        implements MovieView,
        DescriptionDialogFragment.DescriptionFragmentCallbackListener,
        AllDetailsDialogFragment.AllDetailsDialogFragmentCallbackListener,
        RateMovieFragment.RateMovieFragmentListener,
        UserReviewFragment.UserReviewFragmentListener {
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NestedScrollView nestedScrollView;
    private ImageView poster;
    private TextView title;
    private TextView releaseDate;
    private TextView duration;
    private Button order;
    private TextView description;
    private Button readMoreDescription;
    private TextView genre;
    private TextView director;
    private TextView stars;
    private Button allDetails;
    private FloatingActionButton.OnVisibilityChangedListener fabListener = new FloatingActionButton
            .OnVisibilityChangedListener() {
        @Override
        public void onHidden(FloatingActionButton fab) {
            super.onHidden(fab);
            fab.setVisibility(View.INVISIBLE);
        }
    };

    private MovieEntity currentMovieEntity;
    private boolean isLoaded = false;

    private FinishActivityReceiver finishActivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        finishActivityReceiver = new FinishActivityReceiver(this);
        registerReceiver(finishActivityReceiver, new IntentFilter("FinishActivity:PaymentDone"));

        initView();
        getPresenter().getAndDisplayMovie();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(finishActivityReceiver);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus && !isLoaded) {
            initFragment();
            isLoaded = true;
        }
    }

    @Override
    @SuppressLint("PrivateApi")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_movie_options, menu);
        try {
            Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(menu, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addToWatchlist:
                break;
            case R.id.removeFromWatchlist:
                break;
            case R.id.share:
                break;
        }
        return false;
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        RateMovieFragment rateMovieFragment = new RateMovieFragment();
        fragmentManager.beginTransaction().replace(R.id.rateThisMovieContainer, rateMovieFragment).commit();

        UserReviewFragment userReviewFragment = new UserReviewFragment();
        fragmentManager.beginTransaction().replace(R.id.userReviewContainer, userReviewFragment).commit();
    }

    @Override
    public MovieView createView() {
        return this;
    }

    @Override
    public MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshUserReview();
            }
        });
        nestedScrollView = findViewById(R.id.nestedScrollView);
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        releaseDate = findViewById(R.id.releaseDate);
        duration = findViewById(R.id.duration);
        order = findViewById(R.id.order);
        description = findViewById(R.id.description);
        readMoreDescription = findViewById(R.id.readMoreDescription);
        genre = findViewById(R.id.genre);
        director = findViewById(R.id.director);
        stars = findViewById(R.id.stars);
        allDetails = findViewById(R.id.allDetails);
    }

    private void refreshUserReview() {
        Toast.makeText(this, R.string.refreshing_user_review, Toast.LENGTH_LONG).show();
        getPresenter().refreshUserReview(getSupportFragmentManager());
    }

    public void onClickPoster(View view) {
        Log.v("View id", view.getTransitionName() + "");
        transition(view);
    }

    public void onClickOrder(View view) {
        Intent intent = new Intent(this, MovieScheduleActivity.class);
        intent.putExtra("movieTitle", currentMovieEntity.getTitle());
        startActivity(intent);
    }

    public void onClickReadMore(View view) {
        getPresenter().displayDescriptionDialog();
    }

    public void onClickAllDetails(View view) {
        getPresenter().displayAllDetailsDialog();
    }

    public void onClickFAB(View view) {
        nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
        nestedScrollView.fullScroll(NestedScrollView.FOCUS_UP);
        FloatingActionButton fab = (FloatingActionButton) view;
        fab.hide(fabListener);
    }


    private void transition(View view) {
        Intent intent = new Intent(MovieActivity.this, PosterGalleryActivity.class);
        intent.putExtra("movieId", currentMovieEntity.getId());
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MovieActivity.this, view, getString(R.string.transition_poster));
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onDisplayRefreshing() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onFinishRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public void onUpdateMovieInformation(MovieEntity movieEntity) {
        if (movieEntity == null) return;
        this.currentMovieEntity = movieEntity;
        toolbar.setTitle(movieEntity.getTitle());
        Glide.with(this).load(NetworkUtil.GET_MOVIE_POSTER_URL + movieEntity.getTitle()).into(poster);
        title.setText(movieEntity.getTitle());
        releaseDate.setText(movieEntity.getReleaseDate());
        duration.setText(movieEntity.getDuration());
        description.setText(movieEntity.getDescription());
        genre.setText(movieEntity.getGenre());
        director.setText(movieEntity.getDirector());
        stars.setText(movieEntity.getStars());
    }

    @Override
    public void onUpdateOrderButton(MovieScheduleEntity lowestPriceEntity) {
        order.setText(getString(R.string.order_ticket) + lowestPriceEntity.getPrice());
    }

    @Override
    public void onNullResult() {
        order.setText(R.string.no_movie_schedule);
    }

    @Override
    public void onDisplayDescriptionDialog() {
        DescriptionDialogFragment descriptionDialogFragment = new DescriptionDialogFragment();
        descriptionDialogFragment.show(getSupportFragmentManager());
    }

    @Override
    public void onDisplayAllDetailsDialog() {
        AllDetailsDialogFragment allDetailsDialogFragment = new AllDetailsDialogFragment();
        allDetailsDialogFragment.show(getSupportFragmentManager());
    }

    @Override
    public String onGetDescription() {
        return currentMovieEntity.getDescription();
    }

    @Override
    public MovieEntity onGetMovie() {
        return currentMovieEntity;
    }

    @Override
    public String onGetMovieTitle() {
        return getPresenter().getMovieTitle();
    }
}
