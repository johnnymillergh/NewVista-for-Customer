package com.jm.newvista.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.util.ImageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Johnny on 1/29/2018.
 */

public class TopMovieViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ImageView topMovieImageView;
    private TextView topMovieTextView;
    private List<String> topMovieTitles = new ArrayList<>();
    private HashMap<Integer, String> topMoviePoster = new HashMap<>();
    private final int topMovieCount = 5;

    public TopMovieViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_top_movie, container, false);
        topMovieImageView = (ImageView) view.findViewById(R.id.topMovieImageView);
        topMovieTextView = (TextView) view.findViewById(R.id.topMovieTitle);
        if (topMovieTitles.size() == 5) {
            topMovieTextView.setText(topMovieTitles.get(position));
        }
        if (topMoviePoster.size() == 5) {
            Log.v("instantiateItem", position + "");
            String bytesStr = topMoviePoster.get(position + 1);
            Log.v("instantiateItem", bytesStr);
            Glide.with(view).load(ImageUtil.decode(bytesStr)).into(topMovieImageView);
        }
        container.addView(view);
        // set image view size
//        DisplayMetrics metrics = topMovieImageView.getResources().getDisplayMetrics();
//        ViewGroup.LayoutParams params = topMovieImageView.getLayoutParams();
//        params.height = topMovieImageView.getMeasuredHeight();
//        params.height = params.width * 9 / 16;
//        topMovieImageView.setLayoutParams(params);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return topMovieCount;
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

    public HashMap<Integer, String> getTopMoviePoster() {
        return topMoviePoster;
    }

    public void setTopMoviePoster(HashMap<Integer, String> topMoviePoster) {
        this.topMoviePoster = topMoviePoster;
    }
}
