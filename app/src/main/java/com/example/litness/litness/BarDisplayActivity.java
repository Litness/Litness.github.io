package com.example.litness.litness;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.litness.litness.Dialog.CheckInDialog;

import java.util.List;
import java.util.Objects;

public class BarDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_display);

        refreshLayout();

        findViewById(R.id.bar_button_checkin).setOnClickListener(v->
                new CheckInDialog(this, this::updateLitness).show());
    }

    private void refreshLayout() {
        Bar b = Client.activeBar;

        Toolbar toolbar = findViewById(R.id.bar_toolbar);
        ((TextView) toolbar.findViewById(R.id.bar_title)).setText(b.barName);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        //make phone underlined
        SpannableString phone = new SpannableString(b.phone);
        phone.setSpan(new UnderlineSpan(), 0, phone.length(), 0);
        ((TextView) findViewById(R.id.bar_alt_phone)).setText(phone);

        SpannableString address = new SpannableString(b.address);
        address.setSpan(new UnderlineSpan(), 0, address.length(), 0);
        ((TextView) findViewById(R.id.bar_alt_address)).setText(address);

        ((TextView) findViewById(R.id.bar_alt_description)).setText(b.description);
        ((TextView) findViewById(R.id.bar_alt_cover_over)).setText(b.coverOver);
        ((TextView) findViewById(R.id.bar_alt_cover_under)).setText(b.coverUnder);
        if(b.coverUnder.length() < 2) {
            findViewById(R.id.bar_alt_cover_under).setVisibility(View.GONE);
            findViewById(R.id.textView13).setVisibility(View.GONE);
        }


/*        //TEMP to test images
        List<Integer> listPic = new ArrayList<>();
        listPic.add(R.drawable.default_image_thumbnail);
        listPic.add(R.drawable.default_image_thumbnail);
        listPic.add(R.drawable.default_image_thumbnail);
        listPic.add(R.drawable.default_image_thumbnail);*/

        LinearLayout pics = findViewById(R.id.bar_gallery);
        for(int i : b.photos) {
            @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_pics, null, false);
            ((ImageView) v.findViewById(R.id.adapter_image)).setImageResource(i);
            pics.addView(v);
        }


        //get all the events for the day I just set to zero for easy loading
        if(b.days[0/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1*/] != null) {
            LinearLayout events = findViewById(R.id.bar_events);
            for (String cat : b.days[0/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1*/].events) {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_events, null, false);
                ((TextView) v.findViewById(R.id.adapter_alt_event)).setText(cat);
                events.addView(v);
            }


            LinearLayout specials = findViewById(R.id.bar_specials);
            for (String cat : b.days[0/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1*/].specials) {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_specials, null, false);
                ((TextView) v.findViewById(R.id.adapter_alt_special)).setText(cat);
                specials.addView(v);
            }
        }

        ImageView imgLit = findViewById(R.id.bar_litness);
        switch (b.litness) {
            case "1":
                imgLit.setImageResource(R.drawable.meter_1);
                break;
            case "2":
                imgLit.setImageResource(R.drawable.meter_2);
                break;
            case "3":
                imgLit.setImageResource(R.drawable.meter_3);
                break;
            case "4":
                imgLit.setImageResource(R.drawable.meter_4);
                break;
            default:
                imgLit.setImageResource(R.drawable.meter_5);
                break;
        }
    }

    private void updateLitness(List<String> info) {

        //TODO figure out way to force users to chose litness
        Client.activeBar.litness = info.get(0);

        //make sure it is valid input
        if(info.get(1).length() > 0)
            Client.activeBar.coverOver = "$" + info.get(1);
        if(info.get(2).length() > 0)
            Client.activeBar.coverUnder = "$" + info.get(2);
        if(info.get(3).length() > 0)
            Client.activeBar.wait = info.get(3) + " minutes";

        //update that bars info
        Client.barMap.put(Client.activeBar.barName,Client.activeBar);
        refreshLayout();
    }
}
