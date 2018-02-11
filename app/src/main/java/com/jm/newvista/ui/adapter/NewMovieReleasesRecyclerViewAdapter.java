package com.jm.newvista.ui.adapter;

import android.animation.ObjectAnimator;
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
import com.bumptech.glide.request.RequestOptions;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.ui.activity.MainActivity;
import com.jm.newvista.ui.activity.MovieActivity;
import com.jm.newvista.util.GlideBlurTransformation;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 2/7/2018.
 */

public class NewMovieReleasesRecyclerViewAdapter
        extends RecyclerView.Adapter<NewMovieReleasesRecyclerViewAdapter.MyViewHolder> {
    private Context myContext;
    private List<MovieEntity> newMovies;
    private MainActivity mainActivity;

    public NewMovieReleasesRecyclerViewAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (myContext == null) {
            myContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_portrait, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (newMovies != null) {
            final MovieEntity newMovie = newMovies.get(position);
            holder.title.setText(newMovie.getTitle());
            holder.genre.setText(newMovie.getGenre());
            final ImageView poster = holder.poster;
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(myContext, MovieActivity.class);
                    intent.putExtra("movieId", newMovie.getId());
                    intent.putExtra("from", "NewMovieReleases");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(mainActivity, poster, myContext.getString(R.string
                                    .transition_poster));
                    myContext.startActivity(intent, options.toBundle());
                }
            });
            Glide.with(myContext).load(newMovie.getPoster()).transition(withCrossFade()).into(holder.poster);
//            GlideBlurTransformation glideBlurTransformation = new GlideBlurTransformation(myContext);
//            Glide.with(myContext).load(newMovie.getPoster()).apply(RequestOptions.bitmapTransform
//            (glideBlurTransformation)).into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        if (newMovies == null) {
            return 5;
        } else {
            return newMovies.size();
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
                    upAnim.setDuration(150);
                    upAnim.setInterpolator(new DecelerateInterpolator());
                    upAnim.start();
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    ObjectAnimator downAnim = ObjectAnimator.ofFloat(v, "translationZ", 0);
                    downAnim.setDuration(150);
                    downAnim.setInterpolator(new AccelerateInterpolator());
                    downAnim.start();
                    break;
            }
            return false;
        }
    }

    public List<MovieEntity> getNewMovies() {
        return newMovies;
    }

    public void setNewMovies(List<MovieEntity> newMovies) {
        this.newMovies = newMovies;
    }
}
