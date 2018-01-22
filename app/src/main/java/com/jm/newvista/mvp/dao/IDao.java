package com.jm.newvista.mvp.dao;

import java.util.List;

/**
 * Created by Johnny on 1/21/2018.
 */

public interface IDao<EntityType> {
     boolean save(EntityType entity);

    int update(EntityType entity);

    EntityType queryById(EntityType entity);

    int delete(EntityType entity);

    int deleteAll();

    List<EntityType> getAll();
}
