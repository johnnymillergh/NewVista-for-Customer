package com.jm.newvista.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jm.newvista.R;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.model.UserInfoModel;
import com.jm.newvista.mvp.presenter.UserInfoPresenter;
import com.jm.newvista.mvp.view.UserInfoView;
import com.jm.newvista.ui.base.BaseActivity;

public class UserInfoActivity extends BaseActivity<UserInfoModel, UserInfoView, UserInfoPresenter>
        implements UserInfoView {
    private TextView username;
    private TextView email;
    private TextView gender;
    private TextView homeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
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
    public void displayUserInfo(UserEntity userEntity) {

    }
}
