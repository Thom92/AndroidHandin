package com.example.covid19;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi
{
    @GET("/summary")
    Call<List<Countries>> getCountries();
}
