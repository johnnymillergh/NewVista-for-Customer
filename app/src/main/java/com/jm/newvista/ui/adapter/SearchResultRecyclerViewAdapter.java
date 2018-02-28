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

/**
 * Created by Johnny on 2/21/2018.
 */

public class SearchResultRecyclerViewAdapter
        extends RecyclerView.Adapter<SearchResultRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<MovieEntity> searchResultList;
    private Activity activity;

    public SearchResultRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_lanscape, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MovieEntity movieEntity = searchResultList.get(position);

        // Set properties
        holder.title.setText(movieEntity.getTitle());
        holder.genre.setText(movieEntity.getGenre());
        holder.duration.setText(movieEntity.getDuration());
        holder.description.setText(movieEntity.getDescription());
        Glide.with(context).load(NetworkUtil.GET_MOVIE_POSTER_URL + movieEntity.getTitle())
                .into(holder.poster);

        // Set click listener
        final ImageView poster = holder.poster;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieId", movieEntity.getId());
                intent.putExtra("from", "SearchResult");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, poster, context.getString(R.string
                                .transition_poster));
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResultList == null ? 0 : searchResultList.size();
    }

    public void setSearchResultList(List<MovieEntity> searchResultList) {
        this.searchResultList = searchResultList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        CardView cardView;
        ImageView poster;
        TextView title;
        TextView genre;
        TextView duration;
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnTouchListener(this);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            genre = itemView.findViewById(R.id.genre);
            duration = itemView.findViewById(R.id.duration);
            description = itemView.findViewById(R.id.description);
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
