package com.jm.newvista.mvp.model;

import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;

public class OrderHistoryModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    @Override
    public void cancel() {

    }
}
