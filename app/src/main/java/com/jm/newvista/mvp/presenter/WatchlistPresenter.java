package com.jm.newvista.mvp.presenter;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.bean.WatchlistEntity;
import com.jm.newvista.mvp.base.BasePresenter;
import com.jm.newvista.mvp.model.WatchlistModel;
import com.jm.newvista.mvp.view.WatchlistView;
import com.jm.newvista.ui.adapter.WatchlistRecyclerViewAdapter;

import java.util.List;

public class WatchlistPresenter extends BasePresenter<WatchlistModel, WatchlistView> {
    private WatchlistModel watchlistModel;
    private WatchlistView watchlistView;

    public WatchlistPresenter() {
        watchlistModel = new WatchlistModel();
        super.BasePresenter(watchlistModel);
    }

    public void getAndDisplayWatchlist() {
        watchlistView = getView();

        watchlistModel.getWatchlist(new WatchlistModel.GetWatchlistListener() {
            @Override
            public void onSuccess(List<WatchlistEntity> watchlist) {
                watchlistModel.convertWatchlistToMovieEntity(watchlist, movies -> {
                    WatchlistRecyclerViewAdapter adapter = watchlistView.onGetWatchlistRecyclerViewAdapter();
                    adapter.setMovies(movies);
                    adapter.notifyItemMoved(0, movies.size());
                });
            }

            @Override
            public void onNullResult() {
                watchlistView.onMakeToast("Null Result");
            }

            @Override
            public void onFailure(String errorMessage) {
                watchlistView.onMakeToast(errorMessage);
            }
        });
    }

    public void removeWatchlistItem(MovieEntity movieEntity) {
        watchlistModel.remove(movieEntity, new WatchlistModel.RemoveListener() {
            @Override
            public void onSuccess(String message) {
                watchlistView.onMakeToast(message);
            }

            @Override
            public void onFailure(String errorMessage) {
                watchlistView.onMakeToast(errorMessage);
            }
        });
    }
}
