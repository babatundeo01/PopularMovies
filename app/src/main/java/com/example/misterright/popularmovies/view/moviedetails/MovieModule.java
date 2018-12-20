package com.example.misterright.popularmovies.view.moviedetails;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule {

    private final MovieContract.View movieView;

    public MovieModule(MovieContract.View movieView) {
        this.movieView = movieView;
    }

    @MovieScope
    @Provides
    MovieContract.View providesMovieView() {
        return movieView;
    }
}
