package com.jm.newvista.mvp.model;

import com.jm.newvista.bean.SeatEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 2/25/2018.
 */

public class SeatSelectionModel extends BaseModel {
    private MyOkHttp myOkHttp;

    public SeatSelectionModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    public SeatEntity getSeatFromServer() {
        return null;
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }
}
