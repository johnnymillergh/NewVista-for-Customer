package com.jm.newvista.mvp.model;

import com.jm.newvista.mvp.base.BaseModel;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * Created by Johnny on 2/5/2018.
 */

public class MovieModel extends BaseModel {
    MyOkHttp myOkHttp;

    public MovieModel(MyOkHttp myOkHttp) {
        this.myOkHttp = myOkHttp;
    }

    public void getAndSaveMovie() {

    }

    @Override
    public void cancel() {

    }
}
