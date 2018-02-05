package com.jm.newvista.mvp.dao;

import com.jm.newvista.bean.TopMovieEntity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Johnny on 2/2/2018.
 */

public class TopMovieDao implements IDao<TopMovieEntity> {
    @Override
    public boolean save(TopMovieEntity entity) {
        return entity.save();
    }

    @Override
    public int update(TopMovieEntity entity) {
        return 0;
    }

    @Override
    public TopMovieEntity queryById(TopMovieEntity entity) {
        return null;
    }

    @Override
    public int delete(TopMovieEntity entity) {
        return 0;
    }

    @Override
    public int deleteAll() {
        return TopMovieEntity.deleteAll(TopMovieEntity.class);
    }

    @Override
    public List<TopMovieEntity> getAll() {
        return DataSupport.findAll(TopMovieEntity.class);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
