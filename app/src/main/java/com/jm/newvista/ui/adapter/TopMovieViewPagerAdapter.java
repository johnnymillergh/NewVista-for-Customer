package com.jm.newvista.ui.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
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
import com.jm.newvista.ui.activity.MovieActivity;
import com.jm.newvista.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 1/29/2018.
 */

public class TopMovieViewPagerAdapter extends PagerAdapter implements View.OnTouchListener {
    private Context context;
    private CardView topMovieCardView;
    private ImageView topMovieImageView;
    private TextView topMovieTextView;
    private List<String> topMovieTitles = new ArrayList<>();
    private HashMap<Integer, byte[]> topMoviePoster = new HashMap<>();

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        if (context == null) {
            context = container.getContext();
        }
        final View view = LayoutInflater.from(context).inflate(R.layout.item_top_movie, container, false);
        topMovieCardView = view.findViewById(R.id.topMovieCardView);
        topMovieImageView = view.findViewById(R.id.topMovieImageView);
        topMovieTextView = view.findViewById(R.id.topMovieTitle);
        if (topMovieTitles.size() == 5) {
            topMovieTextView.setText(topMovieTitles.get(position));
            Glide.with(context).load(NetworkUtil.GET_TOP_MOVIE_POSTER_URL + topMovieTitles.get(position))
                    .transition(withCrossFade()).into(topMovieImageView);
        }
        topMovieCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("title", topMovieTitles.get(position));
                intent.putExtra("from", "TopMovie");
                context.startActivity(intent);
            }
        });
        topMovieCardView.setOnTouchListener(this);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return topMovieTitles.size() == 0 ? 1 : topMovieTitles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public List<String> getTopMovieTitles() {
        return topMovieTitles;
    }

    public void setTopMovieTitles(List<String> topMovieTitles) {
        this.topMovieTitles = topMovieTitles;
    }

    public HashMap<Integer, byte[]> getTopMoviePoster() {
        return topMoviePoster;
    }

    public void setTopMoviePoster(HashMap<Integer, byte[]> topMoviePoster) {
        this.topMoviePoster = topMoviePoster;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator upAnim = ObjectAnimator.ofFloat(v, "translationZ", 4);
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
