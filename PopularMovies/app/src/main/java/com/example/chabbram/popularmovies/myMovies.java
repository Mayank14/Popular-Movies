package com.example.chabbram.popularmovies;

import java.util.Date;

/**
 * Created by chabbram on 12/4/2016.
 */

public class myMovies {
    private String title;
    private String mthumbnail;
    private String release;
    private String plot;
    private double popularity;
    private int votes;
    private double avg;
    private String poster_URL;
    public myMovies(String title,String release,String plot,double popularity,int votes,
                    double avg,String poster_URL,String mthumbnail) {
        this.title = title;
        this.release = release;
        this.plot = plot;
        this.popularity = popularity;
        this.votes = votes;
        this.avg = avg;
        this.poster_URL = poster_URL;
        this.mthumbnail = mthumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return release;
    }

    public String getPlot() {
        return plot;
    }

    // for displaying the popularity
    public String getPopularity() {
        int pop_percent = (int)Math.round(popularity);
        return Integer.toString(pop_percent)+"%";
    }

    // for calculation and sorting
    public double getPopularity_calc() {
        return popularity;
    }

    public int getVotes() {
        return votes;
    }

    public double getAvg() {
        return avg;
    }

    public String getPoster_URL() {
        return poster_URL;
    }

    public String getMthumbnail() {
        return mthumbnail;
    }
}
