package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;

import java.util.List;

/**
 * Created by Johnny on 2/7/2018.
 */

public class NewMovieReleasesRecyclerViewAdapter
        extends RecyclerView.Adapter<NewMovieReleasesRecyclerViewAdapter.MyViewHolder> {
    private Context myContext;
    private List<MovieEntity> newMovies;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (myContext == null) {
            myContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_portrait, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MovieEntity newMovie = newMovies.get(position);
        holder.title.setText(newMovie.getTitle());
        holder.genre.setText(newMovie.getGenre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext, "New movie card onClick " + newMovie.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(myContext).load(newMovie.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        if (newMovies == null) {
            return 1;
        } else {
            return newMovies.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView poster;
        TextView title;
        TextView genre;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            title = (TextView) itemView.findViewById(R.id.title);
            genre = (TextView) itemView.findViewById(R.id.genre);
        }
    }

    public List<MovieEntity> getNewMovies() {
        return newMovies;
    }

    public void setNewMovies(List<MovieEntity> newMovies) {
        this.newMovies = newMovies;
    }
}
