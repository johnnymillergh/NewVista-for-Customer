package com.jm.newvista.bean;

import org.litepal.crud.DataSupport;

public class MovieEntity  extends DataSupport {
    private int id;
    private String title;
    private String duration;
    private String genre;
    private String director;
    private String stars;
    private String country;
    private String language;
    private String releaseDate;
    private String filmingLocation;
    private String runtime;
    private String aspectRatio;
    private String description;
    private String posterStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFilmingLocation() {
        return filmingLocation;
    }

    public void setFilmingLocation(String filmingLocation) {
        this.filmingLocation = filmingLocation;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterStr() {
        return posterStr;
    }

    public void setPosterStr(String posterStr) {
        this.posterStr = posterStr;
    }

    @Override
    public String toString() {
        return "MovieEntity: " + id + ", " + title + ", " + duration + ", " + genre + ", " + genre;
    }
}
