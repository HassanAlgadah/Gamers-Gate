package com.example.gamersgate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlaceHolderAPI {


    @GET("api/games?page_size=5&")
    Call<game> getgames(@Query("search") String game);

    @GET("api/games/{id}")
    Call<game> getdetails(@Path ("id") String game);
}
