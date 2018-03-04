package com.jm.newvista.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.util.AppUtil;
import com.jm.newvista.util.Constant;

public class AboutActivity extends AppCompatActivity{
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.rate:
                intent.setData(Uri.parse(Constant.APP_URL));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.email:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(Constant.EMAIL));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_intent));
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this, getString(R.string.about_not_found_email), Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.github:
                intent.setData(Uri.parse(Constant.GIT_HUB));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.license:
                final Dialog dialog = new Dialog(this, R.style.DialogFullscreenWithTitle);
                dialog.setTitle(getString(R.string.licenses));
                dialog.setContentView(R.layout.dialog_source_licenses);

                final WebView webView = dialog.findViewById(R.id.webView);
                webView.loadUrl("file:///android_asset/source_licenses.html");

                Button close = dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;

            case R.id.inspiration:
                intent.setData(Uri.parse(Constant.MATERIAL_DESIGN_APP_URL));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;

            case R.id.share:
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                break;
        }
    }
}
