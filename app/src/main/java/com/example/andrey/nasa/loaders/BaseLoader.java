package com.example.andrey.nasa.loaders;

import android.content.Context;
import android.content.Loader;
import android.database.Cursor;

import com.example.andrey.nasa.Objects.Day;

/**
 * Created by andrey on 01.03.16.
 */
public class BaseLoader extends Loader<Day> {

    private Day day;

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(Day day) {


        super.deliverResult(day);

    }

    @Override
    protected void onStartLoading() {
        if (day != null) {
            deliverResult(day);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        if (day != null) {
            day = null;
        }
    }
}
