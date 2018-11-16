package com.example.litness.litness;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class BarDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_display);

        Bar b = Client.activeBar;

        Toolbar toolbar = findViewById(R.id.bar_toolbar);
        ((TextView) toolbar.findViewById(R.id.bar_title)).setText(b.barName);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        ((TextView) findViewById(R.id.bar_alt_phone)).setText(b.phone);
        ((TextView) findViewById(R.id.bar_alt_address)).setText(b.address);
        ((TextView) findViewById(R.id.bar_alt_description)).setText(b.description);
        ((TextView) findViewById(R.id.bar_alt_cover_over)).setText(b.coverOver);
        ((TextView) findViewById(R.id.bar_alt_cover_under)).setText(b.coverUnder);
        if(b.coverUnder.equals("")) {
            findViewById(R.id.bar_alt_cover_under).setVisibility(View.GONE);
            findViewById(R.id.textView13).setVisibility(View.GONE);
        }


        //TEMP to test images
        List<Integer> listPic = new ArrayList<>();
        listPic.add(R.drawable.default_image_thumbnail);
        listPic.add(R.drawable.default_image_thumbnail);
        listPic.add(R.drawable.default_image_thumbnail);
        listPic.add(R.drawable.default_image_thumbnail);

        LinearLayout pics = findViewById(R.id.bar_gallery);
        for(int i : listPic) {
            @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_pics, null, false);
            ((ImageView) v.findViewById(R.id.adapter_image)).setImageResource(i);
            pics.addView(v);
        }


        LinearLayout events = findViewById(R.id.bar_events);
        for(String cat : b.days[(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1].events) {
            @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_events, null, false);
            ((TextView) v.findViewById(R.id.adapter_alt_event)).setText(cat);
            events.addView(v);
        }


        LinearLayout specials = findViewById(R.id.bar_specials);
        for(String cat : b.days[(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1].specials) {
            @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_specials, null, false);
            ((TextView) v.findViewById(R.id.adapter_alt_special)).setText(cat);
            specials.addView(v);
        }

        ImageView imgLit = findViewById(R.id.bar_litness);
        if(b.litness == 1)
            imgLit.setImageResource(R.drawable.meter_1);
        else if(b.litness == 2)
            imgLit.setImageResource(R.drawable.meter_2);
        else if(b.litness == 3)
            imgLit.setImageResource(R.drawable.meter_3);
        else if(b.litness == 4)
            imgLit.setImageResource(R.drawable.meter_4);
        else
            imgLit.setImageResource(R.drawable.meter_5);

    }
}
