package com.jm.newvista.mvp.model;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;

/**
 * Created by Johnny on 3/12/2018.
 */

public class PaymentModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public UserEntity getCurrentLoginUser() {
        UserDao userDao = new UserDao();
        return userDao.getFirst();
    }

    public void postPaymentToServer(PostPaymentListener postPaymentListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("", "");
        myOkHttp.post().url(NetworkUtil.ORDER_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface PostPaymentListener {
        void onSuccess();

        void onFailure();
    }
}
