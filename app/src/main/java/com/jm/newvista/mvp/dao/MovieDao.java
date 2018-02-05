package com.jm.newvista.mvp.dao;

import com.jm.newvista.bean.MovieEntity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Johnny on 2/5/2018.
 */

public class MovieDao implements IDao<MovieEntity> {
    @Override
    public boolean save(MovieEntity entity) {
        return entity.save();
    }

    @Override
    public int update(MovieEntity entity) {
        return updateById(entity);
    }

    private int updateById(MovieEntity entity) {
        int id = entity.getId();
        return entity.update(id);
    }

    public List<MovieEntity> queryByGenre(MovieEntity entity) {
        return DataSupport.where("genre LIKE ?", "%" + entity.getGenre() + "%").find(MovieEntity.class);
    }

    public List<MovieEntity> queryByTitle(MovieEntity entity) {
        return DataSupport.where("title LIKE ?", "%" + entity.getTitle() + "%").find(MovieEntity.class);
    }

    @Override
    public MovieEntity queryById(MovieEntity entity) {
        return null;
    }

    @Override
    public int delete(MovieEntity entity) {
        return 0;
    }

    @Override
    public int deleteAll() {
        return DataSupport.deleteAll(MovieEntity.class);
    }

    @Override
    public List<MovieEntity> getAll() {
        return DataSupport.findAll(MovieEntity.class);
    }

    @Override
    public boolean isEmpty() {
        if (DataSupport.count("movieentity") > 0) {
            return false;
        } else {
            return true;
        }
    }
}
