package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.PaymentModel;
import com.jm.newvista.mvp.view.PaymentView;

/**
 * Created by Johnny on 3/12/2018.
 */

public class PaymentPresenter extends BasePresenter<PaymentModel, PaymentView> {
    private PaymentModel paymentModel;
    private PaymentView paymentView;

    public PaymentPresenter() {
        paymentModel = new PaymentModel();
        super.BasePresenter(paymentModel);
    }
}
