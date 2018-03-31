package com.jm.newvista.mvp.presenter;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.TicketDetailModel;
import com.jm.newvista.mvp.view.TicketDetailView;

/**
 * Created by Johnny on 3/8/2018.
 */

public class TicketDetailPresenter extends BasePresenter<TicketDetailModel, TicketDetailView> {
    private TicketDetailModel ticketDetailModel;
    private TicketDetailView ticketDetailView;

    public TicketDetailPresenter() {
        ticketDetailModel = new TicketDetailModel();
        super.BasePresenter(ticketDetailModel);
    }

    public void updateView() {
        ticketDetailView = getView();

        Intent intent = ticketDetailView.onGetIntent();
        String orderEntityStr = intent.getStringExtra("orderEntity");

        CustomerOrderEntity orderEntity = new Gson().fromJson(orderEntityStr, CustomerOrderEntity.class);
        ticketDetailView.onUpdateView(orderEntity);
    }
}
