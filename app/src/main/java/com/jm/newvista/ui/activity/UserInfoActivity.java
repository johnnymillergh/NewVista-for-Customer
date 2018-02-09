package com.jm.newvista.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jm.newvista.R;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.model.UserInfoModel;
import com.jm.newvista.mvp.presenter.UserInfoPresenter;
import com.jm.newvista.mvp.view.UserInfoView;
import com.jm.newvista.ui.base.BaseActivity;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class UserInfoActivity extends BaseActivity<UserInfoModel, UserInfoView, UserInfoPresenter>
        implements UserInfoView {
    private ImageView avatar;
    private TextView username;
    private TextView email;
    private TextView gender;
    private TextView homeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        getPresenter().displayUserInfo();
    }

    private void initView() {
        avatar = findViewById(R.id.avatar);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        gender = findViewById(R.id.gender);
        homeLocation = findViewById(R.id.homeLocation);
    }

    @Override
    public UserInfoView createView() {
        return this;
    }

    @Override
    public UserInfoPresenter createPresenter() {
        return new UserInfoPresenter();
    }

    @Override
    public void onDisplayUserInfo(UserEntity userEntity) {
        username.setText(userEntity.getUsername());
        email.setText(userEntity.getEmail());
        gender.setText(userEntity.getGender());
        homeLocation.setText(userEntity.getHomeLocation());
        Glide.with(this).load(userEntity.getAvatar()).transition(withCrossFade()).into(avatar);
    }
}
