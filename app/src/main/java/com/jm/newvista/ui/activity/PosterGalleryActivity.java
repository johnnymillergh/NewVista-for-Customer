package com.jm.newvista.ui.activity;

import android.content.Intent;
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
import com.jm.newvista.mvp.model.PosterGalleryModel;
import com.jm.newvista.mvp.presenter.PosterGalleryPresenter;
import com.jm.newvista.mvp.view.PosterGalleryView;
import com.jm.newvista.ui.base.BaseActivity;

public class PosterGalleryActivity
        extends BaseActivity<PosterGalleryModel, PosterGalleryView, PosterGalleryPresenter>
        implements PosterGalleryView {
    private PhotoView poster;
    private TextView title;
    private MovieEntity movieEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_gallery);
        initVIew();
        getPresenter().getAndDisplayMovie();
    }

    private void initVIew() {
        title = findViewById(R.id.title);
        poster = findViewById(R.id.poster);
    }

    @Override
    public PosterGalleryView createView() {
        return this;
    }

    @Override
    public PosterGalleryPresenter createPresenter() {
        return new PosterGalleryPresenter();
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public void onUpdate(MovieEntity movieEntity) {
        title.setText(movieEntity.getTitle());
        Glide.with(this).load(movieEntity.getPoster()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                poster.setImageDrawable(resource);
            }
        });
    }
}
