package com.jm.newvista.mvp.view;

import android.content.Intent;

import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 3/8/2018.
 */

public interface TicketDetailView extends BaseView {
    Intent onGetIntent();

    void onUpdateView(CustomerOrderEntity orderEntity);

    CustomerOrderEntity onGetCurrentOrderEntity();

    void onUpdateQRCode(String plainText);
}
