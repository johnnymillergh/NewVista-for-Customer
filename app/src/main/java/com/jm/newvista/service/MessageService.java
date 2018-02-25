package com.jm.newvista.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.jm.newvista.R;
import com.jm.newvista.bean.MessageEntity;
import com.jm.newvista.util.ApplicationUtil;
import com.jm.newvista.util.MessageServiceUtil;

public class MessageService extends Service implements MessageServiceUtil.MessageServiceUtilCallback {
    private Binder myBinder = new Binder();
    private MessageServiceUtil messageServiceUtil = new MessageServiceUtil();
    private MessageServiceCallbackListener myCallback;
    private NotificationManager notificationManager;

    public MessageService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        startLocalPort();
    }

    private void startLocalPort() {
//        messageServiceUtil.setMyCallback(this);
//        messageServiceUtil.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageServiceUtil.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public void onBindFinished() {
        if (myCallback != null) {
            myCallback.onLocalServerSocketStarted();
            Notification.Builder builder = new Notification.Builder(ApplicationUtil.getContext())
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Notification")
                    .setContentText("Service started.");
            notificationManager.notify(1, builder.build());
        }
    }

    @Override
    public void onGetMessageFromServer(String message) {
        Log.v("MessageService", "onGetMessageFromServer: " + message);
    }

    public class Binder extends android.os.Binder {
        public void startLocalServerSocket() {
            messageServiceUtil.start();
        }

        public MessageService getService() {
            return MessageService.this;
        }
    }

    public void setMyCallback(MessageServiceCallbackListener myCallback) {
        this.myCallback = myCallback;
    }

    public interface MessageServiceCallbackListener {
        void onLocalServerSocketStarted();
    }
}
