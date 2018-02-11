package com.jm.newvista.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;

public class PosterViewActivity extends AppCompatActivity {
    private PhotoView poster;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_view);
        MovieEntity movieEntity = (MovieEntity) getIntent().getSerializableExtra("movie");
        title = findViewById(R.id.title);
        title.setText(movieEntity.getTitle());
        poster = findViewById(R.id.poster);
        Glide.with(this).load(getDrawable(R.drawable.poster_sample)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                poster.setImageDrawable(resource);
            }
        });
    }
}
