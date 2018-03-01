package com.jm.newvista.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.jm.newvista.R;
import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.MovieScheduleEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.MovieModel;
import com.jm.newvista.mvp.view.MovieView;
import com.jm.newvista.ui.fragment.UserReviewFragment;

/**
 * Created by Johnny on 2/11/2018.
 */

public class MoviePresenter extends BasePresenter<MovieModel, MovieView> {
    private MovieModel movieModel;
    private MovieView movieView;
    private MovieEntity movieEntity;

    public MoviePresenter() {
        movieModel = new MovieModel();
        super.BasePresenter(movieModel);
    }

    @SuppressLint("StaticFieldLeak")
    public void getAndDisplayMovie() {
        movieView = getView();
        Intent intent = getView().onGetIntent();
        String from = intent.getStringExtra("from");
        int movieId;
        switch (from) {
            case "NewMovieReleases":
                movieId = intent.getIntExtra("movieId", 0);
                new AsyncTask<Integer, Void, MovieEntity>() {
                    @Override
                    protected MovieEntity doInBackground(Integer... integers) {
                        movieEntity = movieModel.getMovieFromDB(integers[0]);
                        getAndDisplayPriceByMovieTitle(movieEntity.getTitle());
                        return movieEntity;
                    }

                    @Override
                    protected void onPostExecute(MovieEntity movieEntity) {
                        movieView.onUpdateMovieInformation(movieEntity);
                    }
                }.execute(movieId);
                break;
            case "SearchResult":
                movieId = intent.getIntExtra("movieId", 0);
                new AsyncTask<Integer, Void, MovieEntity>() {
                    @Override
                    protected MovieEntity doInBackground(Integer... integers) {
                        movieEntity = movieModel.getMovieFromDB(integers[0]);
                        getAndDisplayPriceByMovieTitle(movieEntity.getTitle());
                        return movieEntity;
                    }

                    @Override
                    protected void onPostExecute(MovieEntity movieEntity) {
                        movieView.onUpdateMovieInformation(movieEntity);
                    }
                }.execute(movieId);
                break;
            case "TopMovie":
                final String title = intent.getStringExtra("title");
                new AsyncTask<Void, Void, MovieEntity>() {
                    @Override
                    protected MovieEntity doInBackground(Void... voids) {
                        movieEntity = movieModel.getMovieFromDB(title);
                        getAndDisplayPriceByMovieTitle(movieEntity.getTitle());
                        return movieEntity;
                    }

                    @Override
                    protected void onPostExecute(MovieEntity movieEntity) {
                        movieView.onUpdateMovieInformation(movieEntity);
                    }
                }.execute();
                break;
            default:
        }
    }

    private void getAndDisplayPriceByMovieTitle(String movieTitle) {
        movieModel.getLowestPriceByMovieTitle(movieTitle, new MovieModel.GetLowestPriceListener() {
            @Override
            public void onSuccess(MovieScheduleEntity lowestPriceEntity) {
                movieView.onUpdateOrderButton(lowestPriceEntity);
            }

            @Override
            public void onNullResult() {
                movieView.onNullResult();
            }

            @Override
            public void onFailure(String errorMessage) {
            }
        });
    }

    // Deprecated
    @Deprecated
    private void getAndDisplayPriceByMovieId(int movieId) {
        movieModel.getLowestPriceByMovieId(movieId, new MovieModel.GetLowestPriceListener() {
            @Override
            public void onSuccess(MovieScheduleEntity lowestPriceEntity) {
                movieView.onUpdateOrderButton(lowestPriceEntity);
            }

            @Override
            public void onNullResult() {
                movieView.onNullResult();
            }

            @Override
            public void onFailure(String errorMessage) {
            }
        });
    }

    public void displayDescriptionDialog() {
        if (movieEntity == null) return;
        getView().onDisplayDescriptionDialog();
    }

    public void displayAllDetailsDialog() {
        if (movieEntity == null) return;
        getView().onDisplayAllDetailsDialog();
    }

    public String getMovieTitle() {
        return movieEntity == null ? null : movieEntity.getTitle();
    }

    public void refreshUserReview(final FragmentManager supportFragmentManager) {
        supportFragmentManager.beginTransaction().replace(R.id.userReviewContainer, new UserReviewFragment())
                .commit();
    }
}
