package com.example.misterright.popularmovies.network;

import com.example.misterright.popularmovies.api.MovieAPI;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    Retrofit provideMovieRetrofit(GsonConverterFactory gsonFactory,
                                  RxJava2CallAdapterFactory rxJavaFactory,
                                  OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(MovieAPI.BASE_URL)
                .addConverterFactory(gsonFactory)
                .addCallAdapterFactory(rxJavaFactory)
                .client(okHttpClient)
                .build();
    }
}
