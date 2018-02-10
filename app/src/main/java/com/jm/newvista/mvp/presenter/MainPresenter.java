package com.jm.newvista.mvp.presenter;

import android.util.Log;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MainModel;
import com.jm.newvista.mvp.model.UserModel;
import com.jm.newvista.mvp.view.MainView;

/**
 * Created by Johnny on 2/6/2018.
 */

public class MainPresenter extends BasePresenter<MainModel, MainView> {
    MainModel mainModel;
    MainView mainView;
    UserModel userModel;

    public MainPresenter() {
        mainModel = new MainModel();
        super.BasePresenter(mainModel);
    }

    public void initNavigationView() {
        mainView = getView();
        userModel = new UserModel();
        UserEntity userEntity = userModel.getFromDB();
        if (userEntity != null) {
            mainView.onUpdateNavigationView(userEntity);
        }
    }

    public void getMovieFromServer() {
        mainView = getView();
        mainModel.getAndSaveMovie(new MainModel.MainModelCallbackListener() {
            @Override
            public void onSaveMovieFinish() {
                Log.v("getAndSaveMovie", getClass() + ", movie saved");
                mainView.onNotifyMovieSaved();
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
