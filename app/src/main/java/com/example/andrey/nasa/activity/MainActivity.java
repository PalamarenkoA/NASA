package com.example.andrey.nasa.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.andrey.nasa.Objects.Day;
import com.example.andrey.nasa.R;
import com.example.andrey.nasa.SetWall;
import com.example.andrey.nasa.loaders.NasaLoaders;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Day> {
    static final int LOADER_ID = 1;
    private Day thisDay;
    ImageView imageView;
    TextView title;
    TextView  explanation;
    TextView date;
    Context context;
    GregorianCalendar calendar;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showDialog(1);
    return true;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        calendar = new GregorianCalendar();
        calendar.setTimeInMillis(Calendar.getInstance().getTimeInMillis());
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);


        title = (TextView) findViewById(R.id.title);
        explanation = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.progress_animation);
        date = (TextView) findViewById(R.id.date);
        connect(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


    }




    private void connect(int year, int month, int day){
        Bundle bundle = new Bundle();
        Toast.makeText(context, dateBilder(year,month,day), Toast.LENGTH_LONG).show();
        bundle.putString("date", dateBilder(year, month, day));
        imageView.setImageResource(R.drawable.progress_animation);
        getLoaderManager().initLoader(LOADER_ID, bundle, this);
    }
    private String dateBilder (int year, int month, int day){
        String mYear = String.valueOf(year);
        String mMonth;
        String mDay;
        if(month>=10){
        mMonth = String.valueOf(month+1);
        }else{
        mMonth = "0"+String.valueOf(month+1);
        }
        if(day>=10){
           mDay = String.valueOf(day);
        }else{
           mDay = "0"+String.valueOf(day);
        }

String date = mYear + "-" + mMonth + "-" + mDay;

    return date;}
    @Override
    public Loader<Day> onCreateLoader(int id, Bundle args) {

            return new NasaLoaders(this,args.getString("date"));

    }



    public void setImgWallpaper(View view){
        SetWall setWall = new SetWall();
        if(thisDay != null){
        setWall.execute(thisDay.getUrl());
            Toast.makeText(this,"Loading",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Зображення не знайдено",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onLoadFinished(Loader<Day> loader, Day data) {
            if(loader.getId() == LOADER_ID){
                if(data != null){
                    thisDay = data;

                    date.setText(thisDay.getDate());
                    title.setText(thisDay.getTitle());
                    explanation.setText(thisDay.getExplanation());
                    Picasso.with(this).load(thisDay.getUrl()).placeholder(R.drawable.progress_animation).into(imageView);
                }else{
                    Toast.makeText(context, "OVER_RATE_LIMIT", Toast.LENGTH_LONG).show();
                }

                getLoaderManager().destroyLoader(loader.getId());
            }
    }

    @Override
    public void onLoaderReset(Loader<Day> loader) {

    }

    protected Dialog onCreateDialog(int id) {
             DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        tpd.getDatePicker().setMaxDate(new Date().getTime());
            return tpd;


    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            connect(year, monthOfYear, dayOfMonth);

        }
    };
}
