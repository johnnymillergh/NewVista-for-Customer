package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MainModel;
import com.jm.newvista.mvp.model.UserModel;
import com.jm.newvista.mvp.view.MainView;
import com.jm.newvista.util.ImageUtil;

/**
 * Created by Johnny on 2/6/2018.
 */

public class MainPresenter extends BasePresenter<MainModel, MainView> {
    MainModel mainModel;

    public MainPresenter() {
        mainModel = new MainModel();
        super.BasePresenter(mainModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void updateNavigationView() {
        new AsyncTask<Void, Void, UserEntity>() {
            @Override
            protected UserEntity doInBackground(Void... voids) {
                UserEntity userEntity = mainModel.getCurrentUser();
                if (userEntity != null) {
                    userEntity.setAvatar(ImageUtil.decode(userEntity.getAvatarStr()));
                    return userEntity;
                } else {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(UserEntity userEntity) {
                getView().onUpdateNavigationView(userEntity);
            }
        }.execute();
    }

    public void getMovieFromServer() {
        mainModel.getAndSaveMovie(new MainModel.MainModelCallbackListener() {
            @Override
            public void onSaveMovieFinish() {
                Log.v("getAndSaveMovie", getClass() + ", movie saved");
                getView().onNotifyMovieSaved();
            }

            @Override
            public void onDeleteData(int status) {
            }
        });
    }

    public void signOut() {
        mainModel.deleteAllData(new MainModel.MainModelCallbackListener() {
            @Override
            public void onSaveMovieFinish() {
            }

            @Override
            public void onDeleteData(int status) {
                if (status > 0) {
                    getView().onSignOutSuccess();
                } else {
                    getView().onSignOutFailure();
                }
            }
        });
    }
}
