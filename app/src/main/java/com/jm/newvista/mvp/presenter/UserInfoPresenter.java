package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.UserInfoModel;
import com.jm.newvista.mvp.view.UserInfoView;
import com.jm.newvista.util.ImageUtil;

/**
 * Created by Johnny on 2/10/2018.
 */

public class UserInfoPresenter extends BasePresenter<UserInfoModel, UserInfoView> {
    UserInfoModel userInfoModel;
    UserInfoView userInfoView;

    public UserInfoPresenter() {
        userInfoModel = new UserInfoModel();
        super.BasePresenter(userInfoModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void displayUserInfo() {
        userInfoView = getView();
        new AsyncTask<Void, Void, UserEntity>() {
            @Override
            protected UserEntity doInBackground(Void... voids) {
                UserEntity userEntity = userInfoModel.getUserFromDB();
                if (userEntity != null) {
                    userEntity.setAvatar(ImageUtil.decode(userEntity.getAvatarStr()));
                }
                return userEntity;
            }

            @Override
            protected void onPostExecute(UserEntity userEntity) {
                userInfoView.onDisplayUserInfo(userEntity);
            }
        }.execute();
    }
}
