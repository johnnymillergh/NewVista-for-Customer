package com.jm.newvista.bean;

import org.litepal.crud.DataSupport;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class MovieScheduleEntity extends DataSupport {
    private int id;
    private int movieId;
    private int auditoriumId;
    private int auditoriumTheaterId;
    private float price;
    private Timestamp showtime;
    private Date dateOfShow;
    private Time timeOfShow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public int getAuditoriumTheaterId() {
        return auditoriumTheaterId;
    }

    public void setAuditoriumTheaterId(int auditoriumTheaterId) {
        this.auditoriumTheaterId = auditoriumTheaterId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getShowtime() {
        return showtime;
    }

    public void setShowtime(Timestamp showtime) {
        this.showtime = showtime;
    }

    public Date getDateOfShow() {
        return dateOfShow;
    }

    public void setDateOfShow(Date dateOfShow) {
        this.dateOfShow = dateOfShow;
    }

    public Time getTimeOfShow() {
        return timeOfShow;
    }

    public void setTimeOfShow(Time timeOfShow) {
        this.timeOfShow = timeOfShow;
    }

    @Override
    public String toString() {
        return "MovieScheduleEntity: "+id + ", " + movieId + ", " + auditoriumId + ", " + auditoriumTheaterId;
    }
}
