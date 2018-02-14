package com.jm.newvista.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jm.newvista.R;
import com.jm.newvista.bean.MessageEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.service.MessageService;

public class MessageServiceTestActivity extends AppCompatActivity {
    private Button start;
    private Button stop;
    private EditText message;
    private Button send;
    private TextView response;

    private MessageService.Binder binder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MessageService.Binder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_service_test);
        initView();
        UserDao userDao = new UserDao();
        userEntity = userDao.getFirst();
    }

    private void initView() {
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        response = findViewById(R.id.response);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, MessageService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void onClickStop(View view) {
        Intent intent = new Intent(this, MessageService.class);
        stopService(intent);
        unbindService(connection);
    }

    public void onClickSend(View view) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(message.getText().toString());
    }
}
