package com.jm.newvista.bean;

public class MovieRankingEntity {
    private String title;
    private String genre;
    private float averageScore;
    private float gross;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public float getGross() {
        return gross;
    }

    public void setGross(float gross) {
        this.gross = gross;
    }
}
