package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.ui.activity.SearchResultActivity;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny on 2/4/2018.
 */

public class GenreRecyclerViewAdapter extends RecyclerView.Adapter<GenreRecyclerViewAdapter.MyViewHolder> {
    private List<String> genres = new ArrayList<>();
    private Context context;

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
        holder.genreChip.setChipText(genres.get(position).toUpperCase());
        holder.genreChip.setOnChipClickListener(new OnChipClickListener() {
            @Override
            public void onChipClick(View v) {
                Toast.makeText(context, "onChipClick " + genres.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SearchResultActivity.class);
                intent.putExtra("from", "Genre");
                intent.putExtra("genre", genres.get(position).toString());
                context.startActivity(intent);
            }
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
}
