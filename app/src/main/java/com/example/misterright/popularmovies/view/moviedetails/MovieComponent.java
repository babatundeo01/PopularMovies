package com.example.misterright.popularmovies.view.moviedetails;

import com.example.misterright.popularmovies.app.AppComponent;

import dagger.Component;

@MovieScope
@Component(dependencies = {AppComponent.class}, modules = {MovieModule.class})
public interface MovieComponent {

    void inject(MovieActivity movieActivity);

}
