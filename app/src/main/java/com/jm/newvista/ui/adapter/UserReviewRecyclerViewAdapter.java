package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jm.newvista.R;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.util.NetworkUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Johnny on 2/16/2018.
 */

public class UserReviewRecyclerViewAdapter extends RecyclerView.Adapter<UserReviewRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<UserReviewEntity> userReviews;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_review, parent, false);
        context = parent.getContext();
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (userReviews != null) {
            UserReviewEntity userReviewEntity = userReviews.get(position);
            holder.score.setText(String.valueOf(userReviewEntity.getScore()));
            holder.datetime.setText(userReviewEntity.getDateTime().toString());
            holder.title.setText(userReviewEntity.getTitle());
            holder.text.setText(userReviewEntity.getText());
            Glide.with(context).load(NetworkUtil.GET_AVATAR_URL + "?id=" + userReviewEntity.getUserId()).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable>
                        transition) {
                    holder.avatar.setImageDrawable(resource);
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    holder.avatar.setImageDrawable(context.getDrawable(R.drawable.ic_account_circle_black_48px));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return userReviews == null ? 0 : userReviews.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<UserReviewEntity> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReviewEntity> userReviews) {
        this.userReviews = userReviews;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView username;
        TextView score;
        TextView datetime;
        TextView title;
        TextView text;

        MyViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.username);
            score = itemView.findViewById(R.id.score);
            datetime = itemView.findViewById(R.id.datetime);
            title = itemView.findViewById(R.id.title);
            text = itemView.findViewById(R.id.text);
        }
    }
}
