package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MovieScheduleDetailModel;
import com.jm.newvista.mvp.view.MovieScheduleDetailView;
import com.jm.newvista.ui.adapter.MovieScheduleDetailRecyclerViewAdapter;
import com.jm.newvista.util.ApplicationUtil;

import java.util.List;

/**
 * Created by Johnny on 3/24/2018.
 */

public class MovieScheduleDetailPresenter extends BasePresenter<MovieScheduleDetailModel, MovieScheduleDetailView> {
    private MovieScheduleDetailModel movieScheduleDetailModel;
    private MovieScheduleDetailView movieScheduleDetailView;

    public MovieScheduleDetailPresenter() {
        movieScheduleDetailModel = new MovieScheduleDetailModel();
        super.BasePresenter(movieScheduleDetailModel);
    }

    public void getAndDisplayMovieScheduleDetail() {
        movieScheduleDetailView = getView();

        Intent intent = movieScheduleDetailView.onGetIntent();
        int auditoriumTheaterId = intent.getIntExtra("auditoriumTheaterId", -1);
        String movieTitle = intent.getStringExtra("movieTitle");

        MovieScheduleEntity entity = new MovieScheduleEntity();
        entity.setAuditoriumTheaterId(auditoriumTheaterId);
        entity.setMovieTitle(movieTitle);

        Log.v("Detail", entity.toString());
        movieScheduleDetailModel.getMovieScheduleDetail(entity, new MovieScheduleDetailModel
                .GetMovieScheduleDetailListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onSuccess(List<MovieScheduleEntity> movieSchedules) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        MovieScheduleDetailRecyclerViewAdapter adapter = movieScheduleDetailView
                                .onGetMovieScheduleDetailRecyclerViewAdapter();
                        adapter.setMovieSchedules(movieSchedules);
                        adapter.notifyItemRangeChanged(0, movieSchedules.size());
                    }
                }.execute();
            }

            @Override
            public void onNullResult() {
                movieScheduleDetailView.onMakeToast(ApplicationUtil.context.getString(R.string.null_result));
            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }
}
