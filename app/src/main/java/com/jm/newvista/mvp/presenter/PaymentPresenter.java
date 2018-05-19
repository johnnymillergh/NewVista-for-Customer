package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jm.newvista.R;
import com.jm.newvista.bean.CustomerOrderEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.PaymentModel;
import com.jm.newvista.mvp.view.PaymentView;
import com.jm.newvista.util.ApplicationUtil;
import com.jm.newvista.util.ImageUtil;
import com.jm.newvista.util.NetworkUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 3/12/2018.
 */

public class PaymentPresenter extends BasePresenter<PaymentModel, PaymentView> {
    private PaymentModel paymentModel;
    private PaymentView paymentView;

    private CustomerOrderEntity currentOrderEntity;

    private Context context = ApplicationUtil.getContext();

    public PaymentPresenter() {
        paymentModel = new PaymentModel();
        super.BasePresenter(paymentModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void updateView() {
        paymentView = getView();

        Intent intent = paymentView.onGetIntent();

        currentOrderEntity = new Gson().fromJson(intent.getStringExtra("orderEntity"),
                CustomerOrderEntity.class);

        String movieTitle = currentOrderEntity.getMovieTitle();

        Date date = currentOrderEntity.getShowtime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:ss aa MMM d, yyyy", Locale.ENGLISH);
        String dateStr = simpleDateFormat.format(date);

        String seat = currentOrderEntity.getSeatLocation();

        DecimalFormat decimalFormat = new DecimalFormat(".00");
        String totalPriceStr = decimalFormat.format(currentOrderEntity.getTotalPrice());

        paymentView.onGetMovieTitle().setText(movieTitle);
        paymentView.onGetShowtime().setText(dateStr);
        paymentView.onGetSeat().setText(seat);
        paymentView.onGetTotalPrice().setText(paymentView.onGetTotalPrice().getText() + totalPriceStr);

        Glide.with(ApplicationUtil.context).load(NetworkUtil.GET_MOVIE_POSTER_URL + movieTitle)
                .transition(withCrossFade()).into(paymentView.onGetPoster());

        new AsyncTask<Void, Void, UserEntity>() {
            @Override
            protected UserEntity doInBackground(Void... voids) {
                UserEntity currentLoginUser = paymentModel.getCurrentLoginUser();
                if (currentLoginUser != null) {
                    currentLoginUser.setAvatar(ImageUtil.decode(currentLoginUser.getAvatarStr()));
                    return currentLoginUser;
                } else return null;
            }

            @Override
            protected void onPostExecute(UserEntity userEntity) {
                if (userEntity != null) {
                    Glide.with(ApplicationUtil.context).load(userEntity.getAvatar())
                            .transition(withCrossFade()).into(paymentView.onGetAvatar());
                }
            }
        }.execute();
    }

    public void postPay(String paymentPassword) {
        paymentModel.postPaymentToServer(currentOrderEntity, paymentPassword, new PaymentModel.PostPaymentListener() {
            @Override
            public void onSuccess(CustomerOrderEntity orderEntity) {
                if (orderEntity.getIsPaid()) {
                    paymentView.onPaymentSuccess();
                }
            }

            @Override
            public void onFailure(int statusCode, String errorMessage) {
                switch (statusCode) {
                    case 700:
                        // Payment password error
                        paymentView.onMakeToast(context.getString(R.string.peyment_error_700));
                        break;
                    case 701:
                        // Fail to pay
                        paymentView.onMakeToast(context.getString(R.string.payment_error_701));
                        break;
                    default:
                }
            }
        });
    }
}
