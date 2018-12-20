package com.example.misterright.popularmovies.view.home;


import com.example.misterright.popularmovies.app.AppComponent;

import dagger.Component;

@HomeScope
@Component(dependencies = {AppComponent.class}, modules = {HomeModule.class})
public interface HomeComponent {

    void inject(HomeActivity homeActivity);

}
