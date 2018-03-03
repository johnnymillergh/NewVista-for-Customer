package com.jm.newvista.ui.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jm.newvista.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Johnny on 3/3/2018.
 */

public class LoadingAlertDialog extends AlertDialog {
    private Context mContext;
    private ProgressBar progressBar;
    private TextView loading;
    private int count = 0;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (count) {
                case 1:
                    loading.setText(R.string.loading1);
                    break;
                case 2:
                    loading.setText(R.string.loading2);
                    break;
                case 3:
                    loading.setText(R.string.loading3);
                    break;
                default:
                    loading.setText(R.string.loading0);
            }
            count++;
            if (count > 3) count = 0;
        }
    };

    public LoadingAlertDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading_alert);
        setCanceledOnTouchOutside(false);

        progressBar = findViewById(R.id.progressBar);
        loading = findViewById(R.id.loading);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 300, 300);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                timer.cancel();
            }
        });
    }
}
