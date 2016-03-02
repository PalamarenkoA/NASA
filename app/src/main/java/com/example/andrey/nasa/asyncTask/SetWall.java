package com.example.andrey.nasa.asyncTask;

import android.app.WallpaperManager;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.andrey.nasa.ApplicationMy;

import java.io.IOException;
import java.net.URL;

/**
 * Created by andrey on 01.03.16.
 */
public class SetWall extends AsyncTask<String,Void,String> {

    WallpaperManager myWallpaperManager;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        myWallpaperManager = WallpaperManager.getInstance(ApplicationMy.context);
    }



    @Override
    protected String doInBackground(String... params) {
        String s;
        try {
            myWallpaperManager.setStream(new URL(params[0]).openStream());
            s= "Зображення збережено!";
        } catch (IOException e) {
            s= "Зображення не збережено!";
            e.printStackTrace();}

        return s;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(ApplicationMy.context,s,Toast.LENGTH_LONG).show();
    }
}