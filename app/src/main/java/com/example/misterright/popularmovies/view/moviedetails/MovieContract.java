package com.example.misterright.popularmovies.view.moviedetails;

import com.example.misterright.popularmovies.model.moviedetails.MovieDetails;

public interface MovieContract {

    interface View {
        void showMovieDetail(MovieDetails movie);
        void showVideo(String videoID);
    }

    interface Presenter {
        void getMovie(int movieId);
        void getMovieVideos(int movieId);
    }
}
