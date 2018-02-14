package com.jm.newvista.mvp.view;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 2/6/2018.
 */

public interface MainView extends BaseView {
    void onNotifyMovieSaved();

    void onUpdateNavigationView(UserEntity userEntity);

    void onSignOutSuccess();

    void onSignOutFailure();

    void onMakeToast(String message);
}
