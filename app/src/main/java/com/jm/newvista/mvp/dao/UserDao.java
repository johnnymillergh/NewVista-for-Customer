package com.jm.newvista.mvp.dao;

import com.jm.newvista.bean.UserEntity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Johnny on 1/21/2018.
 */

public class UserDao implements IDao<UserEntity> {
    @Override
    public boolean save(UserEntity entity) {
        return entity.save();
    }

    @Override
    public int update(UserEntity entity) {
        return 0;
    }

    @Override
    public UserEntity queryById(UserEntity entity) {
        return null;
    }

    @Override
    public int delete(UserEntity entity) {
        return 0;
    }

    @Override
    public int deleteAll() {
        return UserEntity.deleteAll(UserEntity.class);
    }

    @Override
    public List<UserEntity> getAll() {
        return DataSupport.findAll(UserEntity.class);
    }
}
