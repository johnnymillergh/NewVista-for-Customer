package com.jm.newvista.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

public class OrderHistoryModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public void getOrderHistory(GetOrderHistoryListener getOrderHistoryListener) {
        UserDao dao = new UserDao();
        UserEntity currentUser = dao.getFirst();

        HashMap<String, String> params = new HashMap<>();
        params.put("orderOperation", "getJson");
        params.put("email", currentUser.getEmail());

        myOkHttp.post().url(NetworkUtil.ORDER_URL).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<CustomerOrderEntity> entities = new Gson().fromJson(response,
                        new TypeToken<List<CustomerOrderEntity>>() {
                        }.getType());
                if (entities != null) getOrderHistoryListener.onSuccess(entities);
                else getOrderHistoryListener.onNullResult();
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getOrderHistoryListener.onFailure(error_msg);
            }
        });
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetOrderHistoryListener {
        void onSuccess(List<CustomerOrderEntity> orderHistory);

        void onNullResult();

        void onFailure(String errorMessage);
    }
}
