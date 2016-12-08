package com.example.chabbram.popularmovies;

import java.util.Comparator;

/**
 * Created by chabbram on 12/7/2016.
 */

public class sortMovieByRating implements Comparator<myMovies>{
    @Override
    public int compare(myMovies movie1,myMovies movie2) {
        if(movie1.getAvg() > movie2.getAvg()) {
            return -1;
        }
        else if(movie1.getAvg() < movie2.getAvg()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
