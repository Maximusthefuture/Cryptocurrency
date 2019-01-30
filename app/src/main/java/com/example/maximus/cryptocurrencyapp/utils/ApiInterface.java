package com.example.maximus.cryptocurrencyapp.utils;

import com.example.maximus.cryptocurrencyapp.model.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/v1/cryptocurrency/listings/latest")
    Call<CurrencyResponse> getLastestCurrency(@Query("CMC_PRO_API_KEY") String apiKey);

    @GET("v1/cryptocurrency/info")
    Call<CurrencyResponse> getCurrencyDetails(@Path("id") int id, @Query("CMC_PRO_API_KEY") String apiKey);
}
