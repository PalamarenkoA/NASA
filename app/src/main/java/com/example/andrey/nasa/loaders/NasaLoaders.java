package com.example.andrey.nasa.loaders;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.andrey.nasa.Api.ApiFactory;
import com.example.andrey.nasa.Api.NasaService;
import com.example.andrey.nasa.Objects.Day;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by andrey on 01.03.16.
 */
public class NasaLoaders extends BaseLoader {

    private final String day;
    private final NasaService nasaService;

    @Override
    protected void onForceLoad() {
        Call<Day> call = nasaService.days(day);
        call.enqueue(new Callback<Day>() {
            @Override
            public void onResponse(Response<Day> response) {
                if (response.isSuccess()) {
                    deliverResult(response.body());
                } else {
                    deliverResult(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public NasaLoaders(Context context, String day) {
        super(context);
        this.day = day;
        nasaService = ApiFactory.getNasaService();
    }
}
