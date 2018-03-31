package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void postPaymentToServer(CustomerOrderEntity orderEntity, String paymentPassword, PostPaymentListener
            postPaymentListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("orderOperation", "pay");

        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getFirst();
        params.put("email", userEntity.getEmail());

        Date date = orderEntity.getOrderDatetime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        params.put("orderDatetime", dateStr);

        params.put("paymentPassword", paymentPassword);

        myOkHttp.post().url(NetworkUtil.ORDER_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.v("response", response);
                CustomerOrderEntity orderEntity1 = new Gson().fromJson(response, CustomerOrderEntity.class);
                postPaymentListener.onSuccess(orderEntity1);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                postPaymentListener.onFailure(error_msg);
            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface PostPaymentListener {
        void onSuccess(CustomerOrderEntity orderEntity);

        void onFailure(String errorMessage);
    }
}
