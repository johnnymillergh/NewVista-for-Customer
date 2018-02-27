package com.jm.newvista.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.LoginModel;
import com.jm.newvista.mvp.presenter.LoginPresenter;
import com.jm.newvista.mvp.view.LoginView;
import com.jm.newvista.ui.base.BaseActivity;
import com.jm.newvista.util.ApplicationUtil;

public class LoginActivity extends BaseActivity<LoginModel, LoginView, LoginPresenter> implements LoginView {
    private Toolbar toolbar;
    private EditText email;
    private EditText password;
    private EditText serverIp;
    private CheckBox rememberMe;
    private TextView loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        onNotifyPresenterToAutofill();
        Log.v("LoginActivity", (getPresenter().getView() == null) + "");
    }

    private void initView() {
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
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        serverIp = findViewById(R.id.serverIp);
        rememberMe = findViewById(R.id.rememberMe);
        loginStatus = findViewById(R.id.logInStatus);
    }

    public void clickLogin(View view) {
        getPresenter().login();
    }

    public void clickSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void clickFindPassword(View view) {
        Toast.makeText(this, "Function still in construction", Toast.LENGTH_SHORT).show();
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
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
    public String getServerIp() {
        return serverIp.getText().toString().trim();
    }

    @Override
    public void onLoginResultToast(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ApplicationUtil.getContext(), result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoginSuccess() {
        loginStatus.setText(R.string.log_in_success);
        Intent intent = new Intent();
        intent.putExtra("data_returned", Boolean.valueOf("true"));
        setResult(MainActivity.LOGIN_ACTIVITY_CODE, intent);
        finish();
    }

    @Override
    public void onLoginFailure() {
        loginStatus.setText(R.string.log_in_failure);
    }

    @Override
    public boolean onRememberMeChecked() {
        return rememberMe.isChecked();
    }

    @Override
    public void onNotifyPresenterToAutofill() {
        getPresenter().autofill();
    }

    @Override
    public void onAutofillUserInfo(String email, String password) {
        this.email.setText(email);
        this.password.setText(password);
        rememberMe.setChecked(true);
    }
}
