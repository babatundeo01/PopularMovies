package com.example.misterright.popularmovies.view.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.misterright.popularmovies.R;
import com.example.misterright.popularmovies.app.PopularMoviesApp;
import com.example.misterright.popularmovies.model.movies.Result;
import com.example.misterright.popularmovies.view.moviedetails.MovieActivity;

import java.util.List;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private HomeAdapter homeAdapter;

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recycler = findViewById(R.id.recycler);
        homeAdapter = new HomeAdapter(this);
        recycler.setAdapter(homeAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        DaggerHomeComponent.builder()
                .appComponent(PopularMoviesApp.getAppComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);

        homePresenter.getPopularMovies();
    }

    @Override
    public void showPopularMovies(List<Result> popularMovies) {
        homeAdapter.updateDataset(popularMovies);
    }

    @Override
    public void navigateToMovie(int movieId) {
        startActivity(MovieActivity.startMovieActivity(this, movieId));
    }
}
