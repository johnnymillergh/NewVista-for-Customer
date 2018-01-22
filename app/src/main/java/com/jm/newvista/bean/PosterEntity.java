package com.jm.newvista.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by Johnny on 1/21/2018.
 */

public class PosterEntity extends DataSupport {
    private int id;
    private String movieTitle;
    private byte[] poster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }
}
