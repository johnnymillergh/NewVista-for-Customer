package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 1/29/2018.
 */

public class TopMovieViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ImageView topMovieImageView;
    private TextView topMovieTextView;
    private List<String> topMovieTitles = new ArrayList<>();
    private HashMap<Integer, byte[]> topMoviePoster = new HashMap<>();

    public TopMovieViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_top_movie, container, false);
        topMovieImageView = (ImageView) view.findViewById(R.id.topMovieImageView);
        topMovieTextView = (TextView) view.findViewById(R.id.topMovieTitle);
        if (topMovieTitles.size() == 5) {
            topMovieTextView.setText(topMovieTitles.get(position));
        }
        if (topMoviePoster.size() == 5) {
            Log.v("instantiateItem", position + "");
            byte[] bytes = topMoviePoster.get(position + 1);
            Log.v("instantiateItem", bytes.length + "");
            Glide.with(view).load(bytes).transition(withCrossFade()).into(topMovieImageView);
        }
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
}
