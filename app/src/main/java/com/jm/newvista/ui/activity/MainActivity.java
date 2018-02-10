package com.jm.newvista.ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.model.MainModel;
import com.jm.newvista.mvp.presenter.MainPresenter;
import com.jm.newvista.mvp.view.MainView;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.ui.fragment.GenreFragment;
import com.jm.newvista.ui.fragment.NewMovieReleasesFragment;
import com.jm.newvista.ui.fragment.TopMovieFragment;
import com.jm.newvista.util.ImageUtil;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter>
        implements
        NavigationView.OnNavigationItemSelectedListener,
        MaterialSearchBar.OnSearchActionListener,
        MainView,
        TopMovieFragment.TopMovieCallbackListener,
        GenreFragment.GenreFragmentCallbackListener,
        NewMovieReleasesFragment.NewMovieReleasesFragmentCallbackListener {
    public static final int LOGIN_ACTIVITY_CODE = 1;
    private MaterialSearchBar searchBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RelativeLayout splashScreen;
    private ArrayList<String> searchBarSuggestions = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case LOGIN_ACTIVITY_CODE:
                boolean loginFlag = data.getBooleanExtra("data_returned", false);
                if (loginFlag == true) {
                    getPresenter().updateNavigationView();
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSplashScreen();
        initView();
        initTopMovieFragment();
        getPresenter().getMovieFromServer();
        getPresenter().updateNavigationView();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        searchBar = findViewById(R.id.searchBar);
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
    }

    private void initTopMovieFragment() {
        // Get support fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Add top movie fragment
        TopMovieFragment topMovieFragment = new TopMovieFragment();
        fragmentManager.beginTransaction().add(R.id.topMovieContainer, topMovieFragment).commit();

        // Add genre fragment
        GenreFragment genreFragment = new GenreFragment();
        fragmentManager.beginTransaction().add(R.id.genreChipsContainer, genreFragment).commit();
    }

    @Override
    public MainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
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
            final Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
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
                    startActivityForResult(intent, LOGIN_ACTIVITY_CODE);
                }
            }).start();
        } else if (id == R.id.signOutItem) {
            //TODO
            showSignOutDialog();
        } else if (id == R.id.orderItem) {
            //TODO
        } else if (id == R.id.commentItem) {
            //TODO
        } else if (id == R.id.watchlistItem) {
            //TODO
        } else if (id == R.id.settingsItem) {
            //TODO
        } else if (id == R.id.aboutItem) {
            //TODO
        }
        // Close drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showSignOutDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle(R.string.sign_out_title)
                .setMessage(getString(R.string.sign_out_message))
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, R.string.sign_out_cancel_message, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, R.string.sign_out_confirm_message, Toast.LENGTH_SHORT).show();
                        getPresenter().signOut();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
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

    @SuppressLint("RtlHardcoded")
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
        splashScreen = findViewById(R.id.splashScreen);
        splashScreen.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
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
        // TODO: Add module fragment here which is about movie!!!
        Log.v("onNotifyMovieSaved", "Movie saved");
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Add new movie releases fragment
        fragmentManager.beginTransaction().add(R.id.newMovieReleasesContainer, new NewMovieReleasesFragment()).commit();
    }

    @Override
    public void onUpdateNavigationView(UserEntity userEntity) {
        // Find subview of navigation view
        if (userEntity == null) {
            return;
        }
        View headerView = navigationView.getHeaderView(0);
        CircleImageView avatarNavigation = headerView.findViewById(R.id.avatarNavigation);
        TextView usernameNavigation = headerView.findViewById(R.id.usernameNavigation);
        TextView emailNavigation = headerView.findViewById(R.id.emailNavigation);
        // Update view
        Glide.with(this).load(userEntity.getAvatar()).into(avatarNavigation);
        usernameNavigation.setText(userEntity.getUsername());
        emailNavigation.setText(userEntity.getEmail());
    }

    @Override
    public void onSignOutSuccess() {
        Toast.makeText(this, R.string.sign_out_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignOutFailure() {
        Toast.makeText(this, R.string.sign_out_failure, Toast.LENGTH_SHORT).show();
    }
}
