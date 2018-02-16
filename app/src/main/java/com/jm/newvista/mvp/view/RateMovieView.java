package com.jm.newvista.mvp.view;

import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseView;

/**
 * Created by Johnny on 2/16/2018.
 */

public interface RateMovieView extends BaseView {
    void onUpdateAvatar(UserEntity userEntity);

    String getMovieTitle();

    float getScores();

    String getTitle();

    String getText();

    boolean getIsSpoilers();

    void makeToast(String message);
}
