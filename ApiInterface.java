package com.example.dictionary.api;

import com.example.dictionary.modules.Currency;

import java.util.List;
//Retrofit is a REST Client for Java and Android.//
// It makes it relatively easy to retrieve and upload JSON
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("json")
    Call<List<Currency>> getCurrencies();


}
