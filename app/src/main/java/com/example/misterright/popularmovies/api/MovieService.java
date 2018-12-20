package com.example.misterright.popularmovies.api;

import com.example.misterright.popularmovies.model.moviedetails.MovieDetails;
import com.example.misterright.popularmovies.model.movies.PopularMovies;
import com.example.misterright.popularmovies.model.videos.MovieVideos;
import javax.inject.Inject;
import io.reactivex.Observable;

public class MovieService {

    private static final String API_KEY = "db637646bffa9037e9af87ff3295ec21";
    private static final String DEFAULT_LANGUAGE = "en-US";

    private MovieAPI movieAPI;

    @Inject
    public MovieService(MovieAPI movieAPI) {
        this.movieAPI = movieAPI;
    }

    public Observable<PopularMovies> getPopularMovies(){
        return movieAPI.getMovies(API_KEY, DEFAULT_LANGUAGE);
    }

    public Observable<MovieVideos> getMovieVideos(int movieId){
        return movieAPI.getMovieVideos(movieId, API_KEY);
    }

    public Observable<MovieDetails> getMovies(int movieId){
        return movieAPI.getMovie(movieId, API_KEY);
    }
}
