package com.jm.newvista.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jm.newvista.R;

import java.util.List;

/**
 * Created by Johnny on 1/29/2018.
 */

public class TopMovieRecyclerViewAdapter extends RecyclerView.Adapter<TopMovieRecyclerViewAdapter.MyViewHolder> {
    private List<String> movieTitles;

//    public TopMovieRecyclerViewAdapter(List<String> movieTitles) {
//        this.movieTitles = movieTitles;
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_movie, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.movieTitleTextView.setText(movieTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitleTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            movieTitleTextView = (TextView) itemView.findViewById(R.id.topMovieTitle);
        }
    }
}
