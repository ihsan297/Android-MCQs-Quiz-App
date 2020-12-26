package com.example.prayer_times;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class details extends AppCompatActivity {
    TextView tv,city;
    String cit;
    TextView fajar,duhar,asar,maghrib,isha;
    ImageView fajarPtr,duharPtr,asarPtr,maghribPtr,ishaPtr;
    String fjr,dhr,asr,mghrb,ish;
    ImageButton playButton;
    LinearLayout layout;
    ImageView cityPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        cityPic=findViewById(R.id.cityPic);
        fajarPtr=findViewById(R.id.fajar_ptr);
        duharPtr=findViewById(R.id.duhar_ptr);
        asarPtr=findViewById(R.id.asar_ptr);
        maghribPtr=findViewById(R.id.maghrib_ptr);
        ishaPtr=findViewById(R.id.ishaPtr);
        city=findViewById(R.id.tv_selectedCity);
        fajar=findViewById(R.id.fjrtime);
        duhar=findViewById(R.id.zhrtime);
        asar=findViewById(R.id.asrtime);
        maghrib=findViewById(R.id.mghrbtime);
        isha=findViewById(R.id.ishatime);
        layout=findViewById(R.id.prayerTimesLayout);



        playButton=findViewById(R.id.adhanPlay);
        final Intent intent=getIntent();
        String intentCity=intent.getStringExtra("city");

        city.setText(intentCity);
        tv=findViewById(R.id.info);
         cit=city.getText().toString();
         if(intentCity.equals("Abu Dhabi")){
             new Task()