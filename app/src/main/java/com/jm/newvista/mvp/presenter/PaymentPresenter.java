package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.PaymentModel;
import com.jm.newvista.mvp.view.PaymentView;
import com.jm.newvista.util.ApplicationUtil;
import com.jm.newvista.util.ImageUtil;
import com.jm.newvista.util.NetworkUtil;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Johnny on 3/12/2018.
 */

public class PaymentPresenter extends BasePresenter<PaymentModel, PaymentView> {
    private PaymentModel paymentModel;
    private PaymentView paymentView;

    public PaymentPresenter() {
        paymentModel = new PaymentModel();
        super.BasePresenter(paymentModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void updateView() {
        paymentView = getView();

        Intent intent = paymentView.onGetIntent();

        String movieTitle = intent.getStringExtra("movieTitle");
        String showtime = intent.getStringExtra("showtime");
        String seat = intent.getStringExtra("seat");
        String totalPrice = intent.getStringExtra("totalPrice");

        paymentView.onGetMovieTitle().setText(movieTitle);
        paymentView.onGetShowtime().setText(showtime);
        paymentView.onGetSeat().setText(seat);
        paymentView.onGetTotalPrice().setText(paymentView.onGetTotalPrice().getText() + totalPrice);

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
}
