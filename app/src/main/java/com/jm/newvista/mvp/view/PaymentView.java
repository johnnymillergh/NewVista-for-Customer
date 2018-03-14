package com.jm.newvista.mvp.view;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 3/12/2018.
 */

public interface PaymentView extends BaseView {
    Intent onGetIntent();

    TextView onGetMovieTitle();

    TextView onGetShowtime();

    TextView onGetSeat();

    TextView onGetTotalPrice();

    ImageView onGetPoster();

    ImageView onGetAvatar();
}
