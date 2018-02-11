package com.jm.newvista.ui.activity;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;

public class MovieActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        poster = findViewById(R.id.poster);
    }

    public void onClickPoster(View view) {
        Log.v("View id", view.getTransitionName() + "");
        transition(view);
    }

    private void transition(View view) {
        Intent intent = new Intent(MovieActivity.this, PosterViewActivity.class);
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(getString(R.string.movie_title_sample));
        intent.putExtra("movie", movieEntity);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MovieActivity.this, view, getString(R.string.transition_poster));
        startActivity(intent, options.toBundle());
    }
}
