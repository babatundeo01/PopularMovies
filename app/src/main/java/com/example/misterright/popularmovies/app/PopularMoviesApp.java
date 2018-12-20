package com.example.misterright.popularmovies.app;

import android.app.Application;

public class PopularMoviesApp extends Application {

    private static  AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
