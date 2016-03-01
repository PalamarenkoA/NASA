package com.example.andrey.nasa.Api;

import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by andrey on 01.03.16.
 */
public class ApiFactory {

    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;

    private static final OkHttpClient CLIENT = new OkHttpClient();

    static {
        CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    @NonNull
    public static NasaService getNasaService() {
        return getRetrofit().create(NasaService.class);
    }

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov")
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
    }
}