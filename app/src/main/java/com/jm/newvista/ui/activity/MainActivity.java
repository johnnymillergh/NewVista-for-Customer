package com.jm.newvista.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.model.MovieModel;
import com.jm.newvista.mvp.model.UserModel;
import com.jm.newvista.mvp.presenter.MoviePresenter;
import com.jm.newvista.mvp.view.MovieView;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.ui.fragment.GenreFragment;
import com.jm.newvista.ui.fragment.TopMovieFragment;
import com.jm.newvista.util.ImageUtil;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity<MovieModel, MovieView, MoviePresenter>
        implements
        NavigationView.OnNavigationItemSelectedListener,
        MaterialSearchBar.OnSearchActionListener,
        MovieView,
        TopMovieFragment.TopMovieCallbackListener,
        GenreFragment.GenreFragmentCallbackListener {
    private MaterialSearchBar searchBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RelativeLayout splashScreen;
    private ArrayList<String> searchBarSuggestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSplashScreen();
        initView();
        initFragment();
        getPresenter().getMovie();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);
//        searchBar.inflateMenu(R.menu.main);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        View headerView = navigationView.getHeaderView(0);
        CircleImageView avatarNavigation = (CircleImageView) headerView.findViewById(R.id.avatarNavigation);
        TextView usernameNavigation = (TextView) headerView.findViewById(R.id.usernameNavigation);
        TextView emailNavigation = (TextView) headerView.findViewById(R.id.emailNavigation);
        UserModel userModel = new UserModel();
        UserEntity userEntity = userModel.getFromDB();
        if (userEntity != null) {
            Glide.with(this).load(ImageUtil.decode(userEntity.getAvatarStr())).into(avatarNavigation);
            usernameNavigation.setText(userEntity.getUsername());
            emailNavigation.setText(userEntity.getEmail());
        }
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        TopMovieFragment topMovieFragment = new TopMovieFragment();
        fragmentManager.beginTransaction().add(R.id.topMovieContainer, topMovieFragment).commit();

        GenreFragment genreFragment = new GenreFragment();
        fragmentManager.beginTransaction().add(R.id.genreChipsContainer, genreFragment).commit();
    }

    @Override
    public MovieView createView() {
        return this;
    }

    @Override
    public MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.accountItem) {

        } else if (id == R.id.signInItem) {
            final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(320);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                }
            }).start();
        } else if (id == R.id.signOutItem) {

        } else if (id == R.id.orderItem) {

        } else if (id == R.id.commentItem) {

        } else if (id == R.id.watchlistItem) {

        } else if (id == R.id.settingsItem) {

        } else if (id == R.id.aboutItem) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        Toast.makeText(this, "Search: " + text, Toast.LENGTH_SHORT).show();
        searchBarSuggestions.add(text.toString());
        searchBar.disableSearch();
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.disableSearch();
                break;
        }
    }

    private void showSplashScreen() {
        splashScreen = (RelativeLayout) findViewById(R.id.splashScreen);
        splashScreen.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                        alphaAnimation.setDuration(400);
                        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                splashScreen.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        splashScreen.startAnimation(alphaAnimation);
                    }
                });
            }
        }.start();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onNotifyMovieSaved() {
        Log.v("onNotifyMovieSaved", "Movie saved");
    }
}
