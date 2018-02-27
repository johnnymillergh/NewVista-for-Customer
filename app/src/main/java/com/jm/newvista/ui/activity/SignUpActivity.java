package com.jm.newvista.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.SignUpModel;
import com.jm.newvista.mvp.presenter.SignUpPresenter;
import com.jm.newvista.mvp.view.SignUpView;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.util.ApplicationUtil;

public class SignUpActivity extends BaseActivity<SignUpModel, SignUpView, SignUpPresenter> implements SignUpView {
    private Toolbar toolbar;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText password2;
    private EditText serverIp;
    private CheckBox loginNow;
    private TextView signUpStatus;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialView();
    }

    private void initialView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        serverIp = findViewById(R.id.serverIp);
        loginNow = findViewById(R.id.rememberMe);
        signUpStatus = findViewById(R.id.logInStatus);
        signUp = findViewById(R.id.signUp);
    }

    public void clickSignUp(View view) {
        getPresenter().signUp();
    }

    @Override
    public SignUpView createView() {
        return this;
    }

    @Override
    public SignUpPresenter createPresenter() {
        return new SignUpPresenter();
    }

    @Override
    public String getUsername() {
        return username.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public String getPassword2() {
        return password2.getText().toString().trim();
    }

    @Override
    public String getServerIp() {
        return serverIp.getText().toString().trim();
    }

    @Override
    public void onPasswordNotMatched() {
        Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
        password.setText("");
        password2.setText("");
        password.requestFocus();
    }

    @Override
    public void onSignUpResultToast(final String responseMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ApplicationUtil.getContext(), responseMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onSignUpFailure() {

    }
}
