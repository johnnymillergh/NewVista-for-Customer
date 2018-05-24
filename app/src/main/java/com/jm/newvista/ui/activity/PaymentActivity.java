package com.jm.newvista.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.jm.newvista.R;
import com.jm.newvista.alipay.AlipayConfig;
import com.jm.newvista.alipay.AuthResult;
import com.jm.newvista.alipay.OrderInfoUtil2_0;
import com.jm.newvista.alipay.PayResult;
import com.jm.newvista.mvp.model.PaymentModel;
import com.jm.newvista.mvp.presenter.PaymentPresenter;
import com.jm.newvista.mvp.view.PaymentView;
import com.jm.newvista.receiver.FinishActivityReceiver;
import com.jm.newvista.ui.base.BaseActivity;
import com.twisty.ppv.PayPasswordView;

import java.util.Map;

import static com.jm.newvista.alipay.AlipayConfig.APPID;

public class PaymentActivity
        extends BaseActivity<PaymentModel, PaymentView, PaymentPresenter>
        implements PaymentView {
    private Toolbar toolbar;
    private TextView movieTitle;
    private TextView showtime;
    private TextView seat;
    private TextView totalPrice;
    private ImageView poster;
    private ImageView avatar;
    private PayPasswordView payPasswordView;
    private Button clear;

    private FinishActivityReceiver finishActivityReceiver;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AlipayConfig.SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PaymentActivity.this, R.string.alipay_success, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(PaymentActivity.this, OrderHistoryActivity.class);
                        startActivity(intent);

                        intent = new Intent("FinishActivity:PaymentDone");
                        sendBroadcast(intent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PaymentActivity.this, R.string.alipay_failure, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case AlipayConfig.SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PaymentActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PaymentActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        finishActivityReceiver = new FinishActivityReceiver(this);
        registerReceiver(finishActivityReceiver, new IntentFilter("FinishActivity:PaymentDone"));

        initView();

        getPresenter().updateView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(finishActivityReceiver);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener((v) -> {
            finish();
        });

        movieTitle = findViewById(R.id.movieTitle);
        showtime = findViewById(R.id.showtime);
        seat = findViewById(R.id.seat);
        poster = findViewById(R.id.poster);
        avatar = findViewById(R.id.avatar);
        payPasswordView = findViewById(R.id.payPasswordView);
        payPasswordView.setOnInputDoneListener(result -> inputDone(result));
        clear = findViewById(R.id.clear);
        totalPrice = findViewById(R.id.totalPrice);
    }

    private void inputDone(String result) {
        getPresenter().postPay(result);
    }

    public void onClickClear(View view) {
        payPasswordView.clear();
    }

    @Override
    public PaymentView createView() {
        return this;
    }

    @Override
    public PaymentPresenter createPresenter() {
        return new PaymentPresenter();
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public TextView onGetMovieTitle() {
        return movieTitle;
    }

    @Override
    public TextView onGetShowtime() {
        return showtime;
    }

    @Override
    public TextView onGetSeat() {
        return seat;
    }

    @Override
    public TextView onGetTotalPrice() {
        return totalPrice;
    }

    @Override
    public ImageView onGetPoster() {
        return poster;
    }

    @Override
    public ImageView onGetAvatar() {
        return avatar;
    }

    @Override
    public void onPaymentSuccess() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(AlipayConfig.RSA2_PRIVATE) && TextUtils.isEmpty
                (AlipayConfig.RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }
        boolean rsa2 = (AlipayConfig.RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, getPresenter().getTotalPrice());
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? AlipayConfig.RSA2_PRIVATE : AlipayConfig.RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(PaymentActivity.this);
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Log.i("msp", result.toString());

            Message msg = new Message();
            msg.what = AlipayConfig.SDK_PAY_FLAG;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void onMakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
