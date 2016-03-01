package com.example.andrey.nasa;

import android.app.Application;
import android.content.Context;

/**
 * Created by andrey on 01.03.16.
 */
public class ApplicationMy extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
