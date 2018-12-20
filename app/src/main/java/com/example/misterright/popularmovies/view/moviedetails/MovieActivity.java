package com.example.misterright.popularmovies.view.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misterright.popularmovies.R;
import com.example.misterright.popularmovies.app.PopularMoviesApp;
import com.example.misterright.popularmovies.model.moviedetails.MovieDetails;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class MovieActivity extends AppCompatActivity implements MovieContract.View, View.OnClickListener {

    private static final String MOVIE_ID_EXTRA = "com.example.misterright.popularmovies.view.moviedetails.MOVIE_ID_EXTRA";
    private static final int REQ_START_STANDALONE_PLAYER = 1;
    private static final int REQ_RESOLVE_SERVICE_MISSING = 2;
    private static final String YOUTUBE_KEY = "AIzaSyDnChxHjvzPk29YFaOiwwurIim0OwKryd8";

    @Inject
    MoviePresenter moviePresenter;

    private int movieId;
    private ImageView imageView;
    private ImageButton play;
    private TextView movieTitle;
    private TextView movieOverview;
    private String title, overview;

    public static Intent startMovieActivity(Context context, int movieId) {
        Intent movieInt = new Intent(context, MovieActivity.class);
        movieInt.putExtra(MOVIE_ID_EXTRA, movieId);
        return movieInt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        imageView = findViewById(R.id.imageView);
        play = findViewById(R.id.play);
        movieTitle = findViewById(R.id.movieTitle);
        movieOverview = findViewById(R.id.movieOverview);
        play.setOnClickListener(this);

        DaggerMovieComponent.builder()
                .appComponent(PopularMoviesApp.getAppComponent())
                .movieModule(new MovieModule(this))
                .build()
                .inject(this);

        movieId = getMovieId(savedInstanceState);
        if (movieId < 0) {
            throw new RuntimeException("Invalid entry intent missing argument MOVIE_ID_EXTRA");
        } else {
            moviePresenter.getMovie(movieId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MOVIE_ID_EXTRA, movieId);
    }

    @Override
    public void showMovieDetail(MovieDetails movie) {
        title = movie.getTitle();
        overview = movie.getOverview();
        movieTitle.append(title);
        movieOverview.append(overview);
        Picasso.get().load(movie.getPosterURL()).into(imageView);
    }

    @Override
    public void showVideo(String videoID) {
        int startTimeMillis = 0;
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(
                this, YOUTUBE_KEY, videoID, startTimeMillis, true, false);
        if (intent != null) {
            if (canResolveIntent(intent)) {
                startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
            } else {
                YouTubeInitializationResult.SERVICE_MISSING.getErrorDialog(this, REQ_RESOLVE_SERVICE_MISSING).show();
            }
        }
    }

    private boolean canResolveIntent(Intent intent) {
        List<ResolveInfo> resolveInfo = getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }

    private int getMovieId(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return getIntent().getIntExtra(MOVIE_ID_EXTRA, -1);
        } else {
            return savedInstanceState.getInt(MOVIE_ID_EXTRA, -1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                moviePresenter.getMovieVideos(movieId);
                break;
        }
    }
}
