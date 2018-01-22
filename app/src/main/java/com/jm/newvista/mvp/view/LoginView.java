package com.jm.newvista.mvp.view;

import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 1/18/2018.
 */

// Callback interface of view layer.
public interface LoginView extends BaseView {
    String getEmail();

    String getPassword();

    String getServerIp();

    void onLoginResultToast(String result);

    void onLoginSuccess();

    void onLoginFailure();

    boolean onSaveUserChecked();

    void onNotifyPresenterToAutofill();

    void onAutofillUserInfo(String email, String password);
}
