package com.jm.newvista.mvp.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.jm.newvista.R;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.OrderHistoryModel;
import com.jm.newvista.mvp.view.OrderHistoryView;
import com.jm.newvista.ui.adapter.OrderHistoryRecyclerViewAdapter;

import java.util.List;

public class OrderHistoryPresenter extends BasePresenter<OrderHistoryModel, OrderHistoryView> {
    private OrderHistoryModel orderHistoryModel;
    private OrderHistoryView orderHistoryView;

    public OrderHistoryPresenter() {
        orderHistoryModel = new OrderHistoryModel();
        super.BasePresenter(orderHistoryModel);
    }

    public void getAndDisplayOrderHistory() {
        orderHistoryView = getView();

        orderHistoryModel.getOrderHistory(new OrderHistoryModel.GetOrderHistoryListener() {
            @Override
            public void onSuccess(List<CustomerOrderEntity> orderHistory) {
                OrderHistoryRecyclerViewAdapter adapter = orderHistoryView.onGetOrderHistoryRecyclerViewAdapter();
                adapter.setCustomerOrders(orderHistory);

                RecyclerView recyclerView = orderHistoryView.onGetOrderHistoryRecyclerView();
                Context context = recyclerView.getContext();

                LayoutAnimationController controller =
                        AnimationUtils.loadLayoutAnimation(context, R.anim.animation_layout_from_bottom_to_top);

                recyclerView.setLayoutAnimation(controller);
                recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();
            }

            @Override
            public void onNullResult() {
                orderHistoryView.onMakeToast("Null result");
            }

            @Override
            public void onFailure(String errorMessage) {
                orderHistoryView.onMakeToast(errorMessage);
            }
        });
    }
}
