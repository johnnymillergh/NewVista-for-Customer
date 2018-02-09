package com.jm.newvista.mvp.model;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;

/**
 * Created by Johnny on 2/10/2018.
 */

public class UserInfoModel extends BaseModel {

    public UserInfoModel() {
    }

    public UserEntity getUserFromDB() {
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getFirst();
        return userEntity;
    }

    @Override
    public void cancel() {
    }
}
