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
import com.jm.newvista.bean.MovieRankingEntity;
import com.jm.newvista.ui.activity.MovieActivity;
import com.jm.newvista.util.NetworkUtil;
import com.klinker.android.badged_imageview.BadgedImageView;

import net.wujingchao.android.view.SimpleTagImageView;

import java.text.DecimalFormat;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 2/7/2018.
 */

public class TopSellingRecyclerViewAdapter
        extends RecyclerView.Adapter<TopSellingRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<MovieRankingEntity> topSellingMovies;
    private Activity activity;

    public TopSellingRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_with_rating_portrait,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (topSellingMovies != null) {
            final MovieRankingEntity movieEntity = topSellingMovies.get(position);
            holder.title.setText(movieEntity.getTitle());
            holder.genre.setText(movieEntity.getGenre());
            holder.poster.setTagText("No. " + (position + 1));
            DecimalFormat decimalFormat = new DecimalFormat(".0");
            String averageScore = decimalFormat.format(movieEntity.getAverageScore());
            holder.score.setBadge(averageScore);
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
        if (topSellingMovies == null) {
            return 5;
        }
        if (topSellingMovies.size() > 10) {
            return 10;
        }else {
            return topSellingMovies.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        CardView cardView;
        SimpleTagImageView poster;
        BadgedImageView score;
        TextView title;
        TextView genre;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            poster = itemView.findViewById(R.id.poster);
            score = itemView.findViewById(R.id.score);
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

    public List<MovieRankingEntity> getTopSellingMovies() {
        return topSellingMovies;
    }

    public void setTopSellingMovies(List<MovieRankingEntity> topSellingMovies) {
        this.topSellingMovies = topSellingMovies;
    }
}
