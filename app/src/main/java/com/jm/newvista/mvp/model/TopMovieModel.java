package com.jm.newvista.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.TopMovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Johnny on 2/2/2018.
 */

public class TopMovieModel extends BaseModel {
    private MyOkHttp myOkHttp;

    public TopMovieModel() {
        this.myOkHttp = NetworkUtil.myOkHttp;
    }

    public interface TopMovieModelCallback {
        void onFinishLoadingTitle(List<TopMovieEntity> entities);

        void onFinishLoadingPoster(TopMovieEntity entities);
    }

    public void getTopMovieAndSave(final TopMovieModelCallback topMovieModelCallback) {
        HashMap<String, String> params = new HashMap();
        params.put("topMovieOperation", "getAll");
        String url = "http://39.106.218.175" + NetworkUtil.GET_TOP_MOVIE_URL;
        Log.d("getTopMovieAndSave", "myOkHttp==null: " + (myOkHttp == null));
        myOkHttp.post().url(url).params(params).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Log.d("getTopMovieAndSave", "onSuccess");
                List<TopMovieEntity> entities = new Gson().fromJson(response, new
                        TypeToken<List<TopMovieEntity>>() {
                        }.getType());
                topMovieModelCallback.onFinishLoadingTitle(entities);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Log.d("getTopMovieAndSave", "onFailure" + error_msg);
            }
        });
        for (int i = 1; i <= 5; i++) {
            HashMap<String, String> params1 = new HashMap();
            params1.put("topMovieOperation", "getPoster");
            params1.put("id", i + "");
            myOkHttp.post().url("http://39.106.218.175/servlet.customer.GetTopMovie").params(params1).tag(this).enqueue
                    (new RawResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, String response) {
                            TopMovieEntity entity = new Gson().fromJson(response, TopMovieEntity.class);
                            Log.d("getTopMovieAndSave", "entity " + entity.toString());
                            topMovieModelCallback.onFinishLoadingPoster(entity);
                        }

                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            Log.d("getTopMovieAndSave", "Poster " + error_msg);
                        }
                    });
        }
    }

    @Override
    public void cancel() {

    }
}
