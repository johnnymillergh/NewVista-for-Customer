package com.jm.newvista.mvp.model;

import android.util.Log;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Johnny on 2/23/2018.
 */

public class RandomPicksModel extends BaseModel {
    public List<MovieEntity> getRandomPicksFromDB() {
        MovieDao movieDao = new MovieDao();
        List<MovieEntity> fullList = movieDao.getAll();
        List<MovieEntity> resultList = new ArrayList<>();
        Random random = new Random();
        while (resultList.size() <= 10) {
            int randomIndex = random.nextInt(fullList.size());
            MovieEntity randomMovieEntity = fullList.get(randomIndex);
            if (randomMovieEntity != null) resultList.add(fullList.get(randomIndex));
        }
        return resultList;
    }

    @Override
    public void cancel() {
        Log.v("cancel", getClass().toString());
    }
}
