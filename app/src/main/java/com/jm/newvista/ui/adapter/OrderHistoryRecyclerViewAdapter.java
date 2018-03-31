package com.jm.newvista.ui.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.ui.activity.MovieActivity;
import com.jm.newvista.util.NetworkUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 3/31/2018.
 */

public class OrderHistoryRecyclerViewAdapter
        extends RecyclerView.Adapter<OrderHistoryRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<CustomerOrderEntity> customerOrders;

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if (customerOrders != null) {
            final CustomerOrderEntity orderEntity = customerOrders.get(position);
            holder.theaterName.setText(orderEntity.getTheaterName());
            holder.movieTitle.setText(orderEntity.getMovieTitle());

            Date date = orderEntity.getShowtime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:ss aa MMM d, yyyy", Locale.ENGLISH);
            String dateStr = simpleDateFormat.format(date);
            holder.showtime.setText(dateStr);

            holder.auditoriumName.setText(orderEntity.getAuditoriumName());
            holder.seatLocation.setText(orderEntity.getSeatLocation());
            holder.seatLocation.setText(orderEntity.getSeatLocation());

            StringBuffer orderStatusStr = new StringBuffer();
            if (orderEntity.getIsPaid()) orderStatusStr.append("Paid | ");
            else orderStatusStr.append("Unpaid | ");

            if (orderEntity.getIsUsed()) orderStatusStr.append("Used");
            else orderStatusStr.append("Unused");

            holder.orderStatus.setText(orderStatusStr);
            holder.cardView.setOnClickListener(v -> {
//                Intent intent = new Intent(context, MovieActivity.class);
//                intent.putExtra("movieTitle", orderEntity.getTitle());
//                intent.putExtra("from", "NewMovieReleases");
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(activity, poster, context.getString(R.string
//                                .transition_poster));
//                context.startActivity(intent, options.toBundle());
            });

            Glide.with(context).load(NetworkUtil.GET_MOVIE_POSTER_URL + orderEntity.getMovieTitle())
                    .transition(withCrossFade()).into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return customerOrders == null ? 0 : customerOrders.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        CardView cardView;
        TextView theaterName;
        ImageView poster;
        TextView movieTitle;
        TextView showtime;
        TextView auditoriumName;
        TextView seatLocation;
        TextView orderStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            theaterName = itemView.findViewById(R.id.theaterName);
            poster = itemView.findViewById(R.id.poster);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            showtime = itemView.findViewById(R.id.showtime);
            auditoriumName = itemView.findViewById(R.id.auditoriumName);
            seatLocation = itemView.findViewById(R.id.seatLocation);
            orderStatus = itemView.findViewById(R.id.orderStatus);

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

    public List<CustomerOrderEntity> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrderEntity> customerOrders) {
        this.customerOrders = customerOrders;
    }
}
