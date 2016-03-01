package com.example.andrey.nasa.Api;

import com.example.andrey.nasa.Objects.Day;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by andrey on 01.03.16.
 */
public interface NasaService {


    @GET("/planetary/apod?&api_key=DEMO_KEY")
    Call<Day> days(@Query("date") String gps);
}
