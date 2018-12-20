package com.example.misterright.popularmovies.view.moviedetails;

import com.example.misterright.popularmovies.api.MovieService;
import com.example.misterright.popularmovies.model.moviedetails.MovieDetails;
import com.example.misterright.popularmovies.model.videos.MovieVideos;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePresenter implements MovieContract.Presenter {

    @Inject
    MovieService movieService;

    private final MovieContract.View movieView;

    @Inject
    public MoviePresenter(MovieContract.View movieView) {
        this.movieView = movieView;
    }

    @Override
    public void getMovie(int movieId) {
        movieService.getMovies(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetails movieDetails) {
                        movieView.showMovieDetail(movieDetails);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getMovieVideos(int movieId) {
        movieService.getMovieVideos(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieVideos>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieVideos movieVideos) {
                        movieView.showVideo(movieVideos.getResults().get(0).getKey());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
