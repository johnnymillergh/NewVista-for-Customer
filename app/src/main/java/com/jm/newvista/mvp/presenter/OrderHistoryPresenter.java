package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.OrderHistoryModel;
import com.jm.newvista.mvp.view.OrderHistoryView;

public class OrderHistoryPresenter extends BasePresenter<OrderHistoryModel, OrderHistoryView> {
    private OrderHistoryModel orderHistoryModel;
    private OrderHistoryPresenter orderHistoryPresenter;

    public OrderHistoryPresenter() {
        orderHistoryModel = new OrderHistoryModel();
        super.BasePresenter(orderHistoryModel);
    }
}
