package com.jm.newvista.mvp.presenter;

import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.SettingsModel;
import com.jm.newvista.mvp.view.SettingsView;

/**
 * Created by Johnny on 3/23/2018.
 */

public class SettingsPresenter extends BasePresenter<SettingsModel, SettingsView> {
    private SettingsModel settingsModel;
    private SettingsView settingsView;

    public SettingsPresenter() {
        settingsModel = new SettingsModel();
        super.BasePresenter(settingsModel);
    }
}
