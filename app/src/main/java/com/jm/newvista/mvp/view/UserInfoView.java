package com.jm.newvista.mvp.view;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 2/10/2018.
 */

public interface UserInfoView extends BaseView {
    void onDisplayUserInfo(UserEntity userEntity);
}
