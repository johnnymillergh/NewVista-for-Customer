package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.util.NetworkUtil;

import java.util.List;

/**
 * Created by Johnny on 2/21/2018.
 */

public class SearchResultRecyclerViewAdapter
        extends RecyclerView.Adapter<SearchResultRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<MovieEntity> searchResultList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_lanscape, parent, false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieEntity movieEntity = searchResultList.get(position);
        holder.title.setText(movieEntity.getTitle());
        holder.genre.setText(movieEntity.getGenre());
        holder.duration.setText(movieEntity.getDuration());
        holder.description.setText(movieEntity.getDescription());
        Glide.with(context).load(NetworkUtil.GET_MOVIE_POSTER_URL + "?title=" + movieEntity.getTitle())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return searchResultList == null ? 0 : searchResultList.size();
    }

    public void setSearchResultList(List<MovieEntity> searchResultList) {
        this.searchResultList = searchResultList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;
        TextView genre;
        TextView duration;
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            genre = itemView.findViewById(R.id.genre);
            duration = itemView.findViewById(R.id.duration);
            description = itemView.findViewById(R.id.description);
        }
    }
}
