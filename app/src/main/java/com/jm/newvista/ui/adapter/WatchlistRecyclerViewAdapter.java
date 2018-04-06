package com.jm.newvista.ui.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.WatchlistEntity;
import com.jm.newvista.ui.activity.MovieActivity;
import com.jm.newvista.util.NetworkUtil;

import java.util.List;

/**
 * Created by Johnny on 4/4/2018.
 */

public class WatchlistRecyclerViewAdapter
        extends RecyclerView.Adapter<WatchlistRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<MovieEntity> movies;
    private List<WatchlistEntity> watchlist;
    private Activity activity;

    public WatchlistRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_watchlist, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (movies != null) {
            final MovieEntity movieEntity = movies.get(position);

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_recycler_item_show);
            holder.cardView.startAnimation(animation);

            // Set properties
            holder.title.setText(movieEntity.getTitle());
            holder.genre.setText(movieEntity.getGenre());
            Glide.with(context).load(NetworkUtil.GET_MOVIE_POSTER_URL + movieEntity.getTitle())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable>
                                transition) {
                            holder.poster.setImageDrawable(resource);

                            BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                            Bitmap bitmap = bitmapDrawable.getBitmap();
                            Palette palette = Palette.from(bitmap).generate();
                            holder.cardView.setCardBackgroundColor(palette.getDarkVibrantColor(context.getResources()
                                    .getColor(R.color.colorAccent)));
                        }
                    });

            // Set click listener
            final ImageView poster = holder.poster;
            holder.cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieId", movieEntity.getId());
                intent.putExtra("from", "SearchResult");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, poster, context.getString(R.string
                                .transition_poster));
                context.startActivity(intent, options.toBundle());
            });
        }
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    public void setWatchlist(List<WatchlistEntity> watchlist) {
        this.watchlist = watchlist;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        CardView cardView;
        ImageView poster;
        TextView title;
        TextView genre;
        Button remove;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnTouchListener(this);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            genre = itemView.findViewById(R.id.genre);
            remove = itemView.findViewById(R.id.remove);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ObjectAnimator upAnim = ObjectAnimator.ofFloat(v, "translationZ", 8);
                    upAnim.setDuration(50);
                    upAnim.setInterpolator(new DecelerateInterpolator());
                    upAnim.start();
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    ObjectAnimator downAnim = ObjectAnimator.ofFloat(v, "translationZ", 0);
                    downAnim.setDuration(50);
                    downAnim.setInterpolator(new AccelerateInterpolator());
                    downAnim.start();
                    break;
            }
            return false;
        }
    }
}
