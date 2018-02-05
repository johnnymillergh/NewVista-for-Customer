package com.jm.newvista.mvp.model;

import android.util.Log;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Johnny on 1/21/2018.
 */

public class SignUpModel extends BaseModel {
    private MyOkHttp myOkHttp;

    public SignUpModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    public void signUp(UserEntity userEntity, String serverIp, final SignUpCallbackListener signUpCallbackListener) {
        final HashMap params = new HashMap();
        params.put("email", userEntity.getEmail());
        params.put("password", userEntity.getPassword());
        params.put("username", userEntity.getUsername());
        final String url = NetworkUtil.SIGN_UP_URL;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myOkHttp.post().url(url).params(params).tag(this).enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        signUpCallbackListener.onFinish(response.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        signUpCallbackListener.onError(error_msg);
                    }
                });
            }
        }).start();
    }

    @Override
    public void cancel() {
        Log.v("cancel()", getClass() + ": Cancel sign up.");
        myOkHttp.cancel(this);
    }

    public interface SignUpCallbackListener {
        void onFinish(String responseMessage);

        void onError(String errorMessage);
    }
}
