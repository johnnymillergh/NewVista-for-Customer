package com.jm.newvista.ui.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.ui.activity.MovieActivity;
import com.jm.newvista.util.NetworkUtil;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 2/7/2018.
 */

public class NowInTheatersRecyclerViewAdapter
        extends RecyclerView.Adapter<NowInTheatersRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<MovieEntity> moviesInTheaters;
    private Activity activity;

    public NowInTheatersRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_portrait, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (moviesInTheaters != null) {
            final MovieEntity movieEntity = moviesInTheaters.get(position);
            holder.title.setText(movieEntity.getTitle());
            holder.genre.setText(movieEntity.getGenre());
            final ImageView poster = holder.poster;
            holder.cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieTitle", movieEntity.getTitle());
                intent.putExtra("from", "NewMovieReleases");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, poster, context.getString(R.string
                                .transition_poster));
                context.startActivity(intent, options.toBundle());
            });
            Glide.with(context).load(NetworkUtil.GET_MOVIE_POSTER_URL + movieEntity.getTitle())
                    .transition(withCrossFade()).into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        if (moviesInTheaters == null) {
            return 5;
        } else {
            return moviesInTheaters.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        CardView cardView;
        ImageView poster;
        TextView title;
        TextView genre;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            genre = itemView.findViewById(R.id.genre);

            cardView.setOnTouchListener(this);
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

    public List<MovieEntity> getMoviesInTheaters() {
        return moviesInTheaters;
    }

    public void setMoviesInTheaters(List<MovieEntity> moviesInTheaters) {
        this.moviesInTheaters = moviesInTheaters;
    }
}
