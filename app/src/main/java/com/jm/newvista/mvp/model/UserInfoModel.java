package com.jm.newvista.mvp.model;

import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 2/10/2018.
 */

public class UserInfoModel extends BaseModel {
    private MyOkHttp myOkHttp;

    public UserInfoModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }
}
