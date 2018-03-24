package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.ui.activity.SeatSelectionActivity;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Johnny on 2/4/2018.
 */

public class MovieScheduleDetailRecyclerViewAdapter extends RecyclerView
        .Adapter<MovieScheduleDetailRecyclerViewAdapter.MyViewHolder> {
    private List<MovieScheduleEntity> movieSchedules;
    private Context context;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_schedule_detail, parent,
                false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (movieSchedules != null) {
            MovieScheduleEntity movieScheduleEntity = movieSchedules.get(position);

            Date date = movieScheduleEntity.getShowtime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm aa", Locale.ENGLISH);
            String dateStr = simpleDateFormat.format(date);
            holder.showtime.setText(dateStr);

            holder.auditoriumName.setText(movieScheduleEntity.getAuditoriumName());
            holder.price.setText(String.valueOf(movieScheduleEntity.getPrice()));

            holder.purchase.setOnChipClickListener((v) -> {
                Intent intent = new Intent(context, SeatSelectionActivity.class);
                intent.putExtra("movieScheduleId", movieScheduleEntity.getId());
                intent.putExtra("theaterName", movieScheduleEntity.getTheaterName());
                intent.putExtra("auditoriumName", movieScheduleEntity.getAuditoriumName());
                intent.putExtra("auditoriumId", movieScheduleEntity.getAuditoriumId());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return movieSchedules == null ? 0 : movieSchedules.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView showtime;
        TextView auditoriumName;
        TextView price;
        Chip purchase;

        public MyViewHolder(View itemView) {
            super(itemView);
            showtime = itemView.findViewById(R.id.showtime);
            auditoriumName = itemView.findViewById(R.id.auditoriumName);
            price = itemView.findViewById(R.id.price);
            purchase = itemView.findViewById(R.id.purchase);
        }
    }

    public List<MovieScheduleEntity> getMovieSchedules() {
        return movieSchedules;
    }

    public void setMovieSchedules(List<MovieScheduleEntity> movieSchedules) {
        this.movieSchedules = movieSchedules;
    }
}
