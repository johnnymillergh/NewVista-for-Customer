package com.jm.newvista.mvp.model;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;

/**
 * Created by Johnny on 2/11/2018.
 */

public class MovieModel extends BaseModel {
    public MovieEntity getMovieFromDB(int id) {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(id);
        return movieDao.queryById(movieEntity);
    }

    public MovieEntity getMovieFromDB(String title) {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(title);
        return movieDao.queryMovieByTitle(movieEntity);
    }

    @Override
    public void cancel() {

    }
}
