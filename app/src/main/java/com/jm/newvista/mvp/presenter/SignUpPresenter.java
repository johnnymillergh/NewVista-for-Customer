package com.jm.newvista.mvp.presenter;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.SignUpModel;
import com.jm.newvista.mvp.view.SignUpView;

/**
 * Created by Johnny on 1/21/2018.
 */

public class SignUpPresenter extends BasePresenter<SignUpModel, SignUpView> {
    private SignUpModel signUpModel;
    private SignUpView signUpView;

    private String username;
    private String email;
    private String password;
    private String password2;
    private String serverIp;

    public SignUpPresenter() {
        signUpModel = new SignUpModel();
        super.BasePresenter(signUpModel);
    }

    public void signUp() {
        signUpView = getView();
        username = signUpView.getUsername();
        email = signUpView.getEmail();
        password = signUpView.getPassword();
        password2 = signUpView.getPassword2();
        serverIp = signUpView.getServerIp();

        if (password.compareTo(password2) != 0) {
            signUpView.onPasswordNotMatched();
            return;
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            userEntity.setUsername(username);
            signUpModel.signUp(userEntity, signUpView.getServerIp(), new SignUpModel.SignUpCallbackListener() {
                @Override
                public void onFinish(String responseMessage) {
                    if (responseMessage.contains("success")) {
                        signUpView.onSignUpSuccess();
                        signUpView.onSignUpResultToast("All set up. You're ready to go");
                    } else {
                        signUpView.onSignUpFailure();
                        signUpView.onSignUpResultToast(responseMessage);
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    signUpView.onSignUpResultToast(errorMessage);
                }
            });
        }
    }
}
