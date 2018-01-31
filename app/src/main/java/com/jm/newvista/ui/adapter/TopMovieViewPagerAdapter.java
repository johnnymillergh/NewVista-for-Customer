package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jm.newvista.R;

/**
 * Created by Johnny on 1/29/2018.
 */

public class TopMovieViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ImageView topMovieImageView;
    private TextView topMovieTextView;
    private final int topMovieCount = 6;

    public TopMovieViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_movie_card, container, false);
        topMovieImageView = (ImageView) view.findViewById(R.id.topMovieImageView);
        topMovieTextView = (TextView) view.findViewById(R.id.topMovieTitle);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return topMovieCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
