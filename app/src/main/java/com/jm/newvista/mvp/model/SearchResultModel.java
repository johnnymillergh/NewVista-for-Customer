package com.jm.newvista.mvp.model;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;

import java.util.List;

/**
 * Created by Johnny on 2/20/2018.
 */

public class SearchResultModel extends BaseModel {
    private MovieDao movieDao;

    public SearchResultModel() {
        movieDao = new MovieDao();
    }

    public List<MovieEntity> getSearchResultByTitle(MovieEntity movieEntity) {
        List<MovieEntity> resultList = movieDao.queryMoviesByTitle(movieEntity);
        return resultList;
    }

    public List<MovieEntity> getSearchResultByGenre(MovieEntity movieEntity) {
        List<MovieEntity> resultList = movieDao.queryMoviesByGenre(movieEntity);
        return resultList;
    }

    @Override
    public void cancel() {

    }

    public interface SearchResultModelListener {
        void onFinish();
    }
}
