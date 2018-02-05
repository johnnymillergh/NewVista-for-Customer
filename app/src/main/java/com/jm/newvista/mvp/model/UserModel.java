package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/6/2018.
 */

public class UserModel extends BaseModel {
    MyOkHttp myOkHttp;

    public UserModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    public void getAndSave(final UserEntity entity, final UserModelCallbackListener userModelCallbackListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", entity.getEmail());
        params.put("password", entity.getPassword());

        String url = NetworkUtil.USER_INFO_URL;
        myOkHttp.post().url(url).params(params).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                UserEntity userEntity = new Gson().fromJson(response, UserEntity.class);
                save(userEntity, userModelCallbackListener);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    private void save(UserEntity userEntity, UserModelCallbackListener userModelCallbackListener) {
        UserDao dao = new UserDao();
        if (!dao.isEmpty()) {
            dao.deleteAll();
            userEntity.save();
        } else {
            userEntity.save();
        }
        userModelCallbackListener.onFinishSavingUser();
    }

    public UserEntity getFromDB() {
        UserDao userDao = new UserDao();
        List<UserEntity> entities = userDao.getAll();
        return entities.size() == 1 ? entities.get(0) : null;
    }

    @Override
    public void cancel() {
        Log.v("cancel()", getClass().toString());
        myOkHttp.cancel(this);
    }

    public interface UserModelCallbackListener {
        void onFinishSavingUser();
    }
}
