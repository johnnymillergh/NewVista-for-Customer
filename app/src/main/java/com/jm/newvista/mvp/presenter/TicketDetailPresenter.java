package com.jm.newvista.mvp.presenter;

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
}
