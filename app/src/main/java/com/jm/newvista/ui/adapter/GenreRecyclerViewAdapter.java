package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jm.newvista.R;
import com.jm.newvista.ui.activity.SearchResultActivity;
import com.robertlevonyan.views.chip.Chip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny on 2/4/2018.
 */

public class GenreRecyclerViewAdapter extends RecyclerView.Adapter<GenreRecyclerViewAdapter.MyViewHolder> {
    private List<String> genres = new ArrayList<>();
    private Context context;

    private OnChipClickListener onChipClickListener;

    public GenreRecyclerViewAdapter(OnChipClickListener onChipClickListener) {
        this.onChipClickListener = onChipClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.v("onBindViewHolder", "GenreRecyclerViewAdapter: " + position);
        holder.genreChip.setChipText(genres.get(position).toUpperCase());
        holder.genreChip.setOnChipClickListener((v) -> {
            onChipClickListener.onChipClick(v, genres.get(position));
        });
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

    public interface OnChipClickListener {
        void onChipClick(View v, String chipText);
    }
}
