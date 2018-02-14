package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MessageEntity;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.UserEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.mvp.dao.UserDao;
import com.jm.newvista.util.MessageServiceUtil;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/6/2018.
 */

public class MainModel extends BaseModel {
    MyOkHttp myOkHttp;

    public MainModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    public UserEntity getCurrentUser() {
        UserDao userDao = new UserDao();
        return userDao.getFirst();
    }

    public void getAndSaveMovie(final MainModelCallbackListener mainModelCallbackListener) {
        // Prepare parameter
        HashMap<String, String> params = new HashMap();
        params.put("movieOperation", "getAll");
        String url = NetworkUtil.GET_MOVIE_URL;
        // About to post
        Log.d("getAndSaveMovie", "myOkHttp==null: " + (myOkHttp == null));
        myOkHttp.post().url(url).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.d("getAndSaveMovie", getClass() + "onSuccess");
                List<MovieEntity> entities = new Gson().fromJson(response,
                        new TypeToken<List<MovieEntity>>() {
                        }.getType());
                saveMovie(entities, mainModelCallbackListener);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.d("getAndSaveMovie", "onFailure" + error_msg);
            }
        });
    }

    private void saveMovie(List<MovieEntity> entities, MainModelCallbackListener mainModelCallbackListener) {
        MovieDao dao = new MovieDao();
        if (dao.recordCount() == entities.size()) {
            // No need to save movie records
            mainModelCallbackListener.onSaveMovieFinish();
        } else if (!dao.isEmpty()) {
            // Delete all movie records
            dao.deleteAll();
            // Save
            dao.saveAll(entities);
            mainModelCallbackListener.onSaveMovieFinish();
        } else {
            // Database didn't have any movie records before, save directly
            dao.saveAll(entities);
            mainModelCallbackListener.onSaveMovieFinish();
        }
    }

    public void deleteAllData(MainModelCallbackListener mainModelCallbackListener) {
        UserDao userDao = new UserDao();
        int status = userDao.deleteAll();
        mainModelCallbackListener.onDeleteData(status);
    }

    public void sendLocalServerSocketInfoToWebServer() {
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.getFirst();

        if (userEntity != null) {
            HashMap<String, String> params = new HashMap<>();
            params.put("email", userEntity.getEmail());
            params.put("password", userEntity.getPassword());
            params.put("port", MessageServiceUtil.localPort + "");

            myOkHttp.post().url(NetworkUtil.SECONDARY_LOGON_URL).params(params).enqueue(new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    if (response.contains("success")) {
                        Log.v("sendLocalServerSocket", "sendLocalServerSocketInfoToWebServer: success");
                    } else {
                        Log.v("sendLocalServerSocket", "sendLocalServerSocketInfoToWebServer: failure");
                    }
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    Log.v("Network", "sendLocalServerSocketInfoToWebServer: network failure");
                }
            });
        }
    }

    @Override
    public void cancel() {
        Log.v("cancel", getClass().toString());
        myOkHttp.cancel(this);
    }

    public interface MainModelCallbackListener {
        void onSaveMovieFinish();

        void onDeleteData(int status);
    }
}
