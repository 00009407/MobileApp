package com.example.dictionary.di;

import com.example.dictionary.api.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    //Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON //
    // (or other structured data) via a REST based webservice.
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://cbu.uz/uzc/arkhiv-kursov-valyut/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiInterface provideApi(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
