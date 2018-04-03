package com.jm.newvista.mvp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jm.newvista.bean.MovieRankingEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;
import com.jm.newvista.util.NetworkUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.List;

public class TopSellingModel extends BaseModel {
    private MyOkHttp myOkHttp = NetworkUtil.myOkHttp;

    public void getTopSelling(GetTopSellingListener getTopSellingListener) {
        myOkHttp.get().url(NetworkUtil.GET_TOP_SELLING_URL).tag(this).enqueue(new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                List<MovieRankingEntity> topSelling = new Gson().fromJson(response,
                        new TypeToken<List<MovieRankingEntity>>() {
                        }.getType());

                if (topSelling.size() == 0) getTopSellingListener.onNullResult();
                else getTopSellingListener.onSuccess(topSelling);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                getTopSellingListener.onFailure(error_msg);
            }
        });
    }

    public void convertToChineseTitle(List<MovieRankingEntity> topSelling, ConvertListener convertListener) {
        MovieDao movieDao = new MovieDao();
        for (int i = 0; i < topSelling.size(); i++) {
            MovieRankingEntity mre = topSelling.get(i);
            String titleCHS = movieDao.findTitleCHSByTitle(mre.getTitle());
            mre.setTitle(titleCHS);
        }
        convertListener.onFinish(topSelling);
    }

    @Override
    public void cancel() {
        myOkHttp.cancel(this);
    }

    public interface GetTopSellingListener {
        void onSuccess(List<MovieRankingEntity> topSelling);

        void onNullResult();

        void onFailure(String errorMessage);
    }

    public interface ConvertListener {
        void onFinish(List<MovieRankingEntity> topSelling);
    }
}
