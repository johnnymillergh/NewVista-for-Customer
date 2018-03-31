package com.jm.newvista.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FinishActivityReceiver extends BroadcastReceiver {
    private Activity activity;

    public FinishActivityReceiver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.finish();
    }
}
