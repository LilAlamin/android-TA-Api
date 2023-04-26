package com.example.ta_sinarmas.rest;

import com.example.ta_sinarmas.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/3/movie/{category}")
    Call<Response> getmovie(
            @Path("category")String category,
            @Query("api_key")String api_key,
            @Query("language")String language,
            @Query("age")int page
    );
}
