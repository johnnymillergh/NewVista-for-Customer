package com.jm.newvista.mvp.model;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;

/**
 * Created by Johnny on 2/11/2018.
 */

public class PosterGalleryModel extends BaseModel {
    public MovieEntity getCurrentMovieFromDB(int movieId) {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(movieId);
        return movieDao.queryById(movieEntity);
    }

    @Override
    public void cancel() {

    }
}
