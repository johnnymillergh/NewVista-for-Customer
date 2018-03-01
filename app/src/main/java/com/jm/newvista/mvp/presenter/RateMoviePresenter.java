package com.jm.newvista.mvp.presenter;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import com.jm.newvista.R;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.RateMovieModel;
import com.jm.newvista.mvp.view.RateMovieView;
import com.jm.newvista.util.ApplicationUtil;

import java.util.HashMap;
import java.util.jar.Attributes;

/**
 * Created by Johnny on 2/16/2018.
 */

public class RateMoviePresenter extends BasePresenter<RateMovieModel, RateMovieView> {
    private RateMovieModel rateMovieModel;
    private RateMovieView rateMovieView;
    private SoundPool soundPool;
    private HashMap<Integer, Integer> hashMap;

    public RateMoviePresenter() {
        rateMovieModel = new RateMovieModel();
        super.BasePresenter(rateMovieModel);
        initSoundPool();
    }

    private void initSoundPool() {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(2);
        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
        builder.setAudioAttributes(attrBuilder.build());
        soundPool = builder.build();
        hashMap = new HashMap<Integer, Integer>();
        hashMap.put(1, soundPool.load(ApplicationUtil.context, R.raw.send_message, 1));
        hashMap.put(2, soundPool.load(ApplicationUtil.context, R.raw.error, 1));
    }

    public void displayUserAvatar() {
        UserEntity decodedCurrentUser = rateMovieModel.getDecodedUserInfo();
        if (decodedCurrentUser != null) {
            getView().onUpdateAvatar(decodedCurrentUser);
        }
    }

    public void submitUserReview() {
        rateMovieView = getView();

        // Get parameters
        String movieTitle = rateMovieView.getMovieTitle();
        int score = (int) (rateMovieView.getScores() * 2);
        String title = rateMovieView.getTitle();
        String text = rateMovieView.getText();
        boolean isSpoilers = rateMovieView.getIsSpoilers();

        // Prepare parameters
        UserReviewEntity userReviewEntity = new UserReviewEntity();
        userReviewEntity.setScore(score);
        userReviewEntity.setTitle(title);
        userReviewEntity.setText(text);
        userReviewEntity.setIsSpoilers(isSpoilers);

        rateMovieModel.postUserReview(movieTitle, userReviewEntity, new RateMovieModel.PostUserReviewListener() {
            @Override
            public void onPostSuccess(String message) {
                playSoundEffect(1);
                rateMovieView.makeToast(message);
                rateMovieView.onClearReview();
                rateMovieView.makeToast(ApplicationUtil.getContext()
                        .getString(R.string.pull_down_to_refresh_user_review));
            }

            @Override
            public void onPostFailure(String message) {
                playSoundEffect(2);
                rateMovieView.makeToast(message);
                rateMovieView.onClearReview();
            }
        });
    }

    private void playSoundEffect(int mediaId) {
        AudioManager audioManager = (AudioManager) ApplicationUtil.context.getSystemService(Context.AUDIO_SERVICE);
        // Get max volume
        float streamVolumeMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = streamVolumeMax;
        // Play sound
        switch (mediaId) {
            case 1:
                soundPool.play(hashMap.get(1), volume, volume, 1, 0, 1.0f);
                break;
            case 2:
                soundPool.play(hashMap.get(2), volume, volume, 1, 0, 1.0f);
                break;
        }
    }
}
