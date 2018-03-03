package com.jm.newvista.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jm.newvista.R;
import com.jm.newvista.util.AppUtil;

public class AboutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        scrollView = findViewById(R.id.scrollView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_from_bottom_to_top);
        scrollView.setAnimation(animation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(600);

        TextView version = findViewById(R.id.version);
        version.setText(AppUtil.getVersionName(this));
        version.startAnimation(alphaAnimation);
    }
}
