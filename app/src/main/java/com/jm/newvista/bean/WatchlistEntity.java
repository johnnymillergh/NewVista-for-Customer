package com.jm.newvista.bean;

import org.litepal.crud.DataSupport;

public class WatchlistEntity extends DataSupport {
    private int id;
    private int userId;
    private String movieTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
