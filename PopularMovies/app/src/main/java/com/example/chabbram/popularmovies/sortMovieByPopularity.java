package com.example.chabbram.popularmovies;

import java.util.Comparator;

/**
 * Created by chabbram on 12/7/2016.
 */

public class sortMovieByPopularity implements Comparator<myMovies> {
    @Override
    public int compare(myMovies movie1, myMovies movie2) {
        if(movie1.getPopularity_calc() > movie2.getPopularity_calc()) {
            return -1;
        }
        else if(movie1.getPopularity_calc() < movie2.getPopularity_calc()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
