package com.example.misterright.popularmovies.view.home;

import com.example.misterright.popularmovies.api.MovieService;
import com.example.misterright.popularmovies.model.movies.PopularMovies;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    @Inject
    MovieService movieService;

    private final HomeContract.View homeView;

    @Inject
    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getPopularMovies() {
        movieService.getPopularMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopularMovies>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PopularMovies popularMovies) {
                        homeView.showPopularMovies(popularMovies.getResults());
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
