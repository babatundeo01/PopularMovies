package com.example.misterright.popularmovies.view.home;

import com.example.misterright.popularmovies.model.movies.Result;

import java.util.List;

public interface HomeContract {

    interface View{
        void showPopularMovies(List<Result> popularMovies);
        void navigateToMovie(int movieId);
    }

    interface Presenter{
        void getPopularMovies();
    }
}
