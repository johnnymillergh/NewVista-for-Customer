package com.jm.newvista.mvp.model;

import android.util.Log;

import com.jm.newvista.bean.MovieEntity;
import com.jm.newvista.mvp.base.BaseModel;
import com.jm.newvista.mvp.dao.MovieDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny on 2/23/2018.
 */

public class NewMovieReleasesActivityModel extends BaseModel {
    List<MovieEntity> newMovieList = new ArrayList<>();

    public List<MovieEntity> getNewMovie() {
        MovieDao dao = new MovieDao();
        List<MovieEntity> entities = dao.getAll();
        int endIndex = entities.size();
        int startIndex = endIndex - 10;
        if (newMovieList.size() != 0) {
            newMovieList.clear();
        }
        // Reverse list item
        for (int i = endIndex - 1; i >= startIndex; i--) {
            newMovieList.add(entities.get(i));
        }
        return newMovieList;
    }

    @Override
    public void cancel() {
        Log.v("cancel", getClass().toString());
    }
}
