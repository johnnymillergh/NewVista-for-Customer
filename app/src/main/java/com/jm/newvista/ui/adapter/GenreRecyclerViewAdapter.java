package com.jm.newvista.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jm.newvista.R;
import com.robertlevonyan.views.chip.Chip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny on 2/4/2018.
 */

public class GenreRecyclerViewAdapter extends RecyclerView.Adapter<GenreRecyclerViewAdapter.MyViewHolder> {
    List<String> genres = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.genreChip.setChipText(genres.get(position).toUpperCase());
    }

    @Override
    public int getItemCount() {
        return genres.size() == 0 ? 1 : genres.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        Chip genreChip;

        public MyViewHolder(View itemView) {
            super(itemView);
            genreChip = (Chip) itemView.findViewById(R.id.genreChip);
        }
    }

    public List<String> getGenres() {
        return genres;
    }
}
