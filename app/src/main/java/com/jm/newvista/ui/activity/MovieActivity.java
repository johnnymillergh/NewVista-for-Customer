package com.jm.newvista.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
import com.jm.newvista.mvp.model.MovieModel;
import com.jm.newvista.mvp.presenter.MoviePresenter;
import com.jm.newvista.mvp.view.MovieView;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.ui.fragment.AllDetailsDialogFragment;
import com.jm.newvista.ui.fragment.DescriptionDialogFragment;
import com.jm.newvista.ui.fragment.RateMovieFragment;
import com.jm.newvista.ui.fragment.UserReviewFragment;

public class MovieActivity
        extends BaseActivity<MovieModel, MovieView, MoviePresenter>
        implements MovieView,
        DescriptionDialogFragment.DescriptionFragmentCallbackListener,
        AllDetailsDialogFragment.AllDetailsDialogFragmentCallbackListener,
        RateMovieFragment.RateMovieFragmentListener,
        UserReviewFragment.UserReviewFragmentListener {
    private Toolbar toolbar;
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

    private FrameLayout rateThisMovieContainer;
    private FrameLayout userReviewContainer;

    private MovieEntity currentMovieEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initView();
        getPresenter().getAndDisplayMovie();
        initFragment();
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

    public void onClickPoster(View view) {
        Log.v("View id", view.getTransitionName() + "");
        transition(view);
    }

    public void onClickOrder(View view) {
        Toast.makeText(this, "onClickOrder", Toast.LENGTH_SHORT).show();
    }

    public void onClickReadMore(View view) {
        getPresenter().displayDescriptionDialog();
    }

    public void onClickAllDetails(View view) {
        getPresenter().displayAllDetailsDialog();
    }

    private void transition(View view) {
        Intent intent = new Intent(MovieActivity.this, PosterGalleryActivity.class);
        intent.putExtra("movieId", currentMovieEntity.getId());
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MovieActivity.this, view, getString(R.string.transition_poster));
        startActivity(intent, options.toBundle());
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
        Glide.with(this).load(movieEntity.getPoster()).into(poster);
        title.setText(movieEntity.getTitle());
        releaseDate.setText(movieEntity.getReleaseDate());
        duration.setText(movieEntity.getDuration());
        description.setText(movieEntity.getDescription());
        genre.setText(movieEntity.getGenre());
        director.setText(movieEntity.getDirector());
        stars.setText(movieEntity.getStars());
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
