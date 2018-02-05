package com.jm.newvista.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.LoginModel;
import com.jm.newvista.mvp.model.UserModel;
import com.jm.newvista.mvp.view.LoginView;

/**
 * Created by Johnny on 1/18/2018.
 */

public class LoginPresenter extends BasePresenter<LoginModel, LoginView> {
    private LoginModel loginModel;
    private LoginView loginView;
    private UserModel userModel;
    private UserEntity userEntity;

    public LoginPresenter() {
        loginModel = new LoginModel();
        userEntity = new UserEntity();
        super.BasePresenter(loginModel);
    }

    public void login() {
        loginView = getView();
        userEntity.setEmail(loginView.getEmail());
        userEntity.setPassword(loginView.getPassword());
        final boolean rememberMe = loginView.onRememberMeChecked();

        this.loginModel.login(userEntity, loginView.getServerIp(), new LoginModel.LoginCallbackListener() {
            @Override
            public void onFinish(String responseMessage) {
                if (responseMessage.contains("success")) {
                    userModel = new UserModel();
                    userModel.getAndSave(userEntity, new UserModel.UserModelCallbackListener() {
                        @Override
                        public void onFinishSavingUser() {
                            Log.v("onFinishSavingUser", getClass().toString());
                        }
                    });
                    loginView.onLoginSuccess();
                } else {
                    loginView.onLoginFailure();
                }
                loginView.onLoginResultToast(responseMessage);
            }

            @Override
            public void onError(String errorMessage) {
                loginView.onLoginResultToast(errorMessage);
            }
        });
    }

    public void saveUser() {
        loginView = getView();
        String email = loginView.getEmail();
        String password = loginView.getPassword();
        if (email.compareTo("") != 0 || password.compareTo("") != 0) {
            userEntity.setEmail(loginView.getEmail());
            userEntity.setPassword(loginView.getPassword());
            loginModel.saveUser(userEntity);
        }
    }

    public void autofill() {
        if (loginModel.hasUser()) {
            loginView = getView();
            UserEntity userEntity = loginModel.loadUser();
            loginView.onAutofillUserInfo(userEntity.getEmail(), userEntity.getPassword());
        }
    }
}
