package com.jm.newvista.mvp.model;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 3/12/2018.
 */

public class PaymentModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public UserEntity getCurrentLoginUser() {
        UserDao userDao = new UserDao();
        return userDao.getFirst();
    }

    @Override
    public void cancel() {

    }
}
