package com.example.misterright.popularmovies.api;

import com.example.misterright.popularmovies.model.moviedetails.MovieDetails;
import com.example.misterright.popularmovies.model.movies.PopularMovies;
import com.example.misterright.popularmovies.model.videos.MovieVideos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    String BASE_URL = "https://api.themoviedb.org/3/";

    //movie/popular?api_key=<<api_key>>&language=en-US
    @GET("movie/popular")
    Observable<PopularMovies> getMovies(@Query("api_key") String API_KEY, @Query("language") String language);

    //movie/{movie_id}/videos?api_key=<<api_key>>
    @GET("movie/{movieId}/videos")
    Observable<MovieVideos> getMovieVideos(@Path("movieId") int movieId, @Query("api_key") String API_KEY);

    //movie/335983?api_key=<<api_key>>
    @GET("movie/{movieId}")
    Observable<MovieDetails> getMovie(@Path("movieId") int movieId, @Query("api_key") String API_KEY);

}
