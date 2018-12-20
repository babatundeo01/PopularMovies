package com.example.misterright.popularmovies.app;

import android.content.Context;

import com.example.misterright.popularmovies.api.MovieModule;
import com.example.misterright.popularmovies.api.MovieService;
import com.example.misterright.popularmovies.network.NetworkModule;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class, MovieModule.class})
public interface AppComponent {

    Context appContext();
    MovieService movieAPI();

}
