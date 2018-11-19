package com.example.litness.litness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
import com.example.litness.litness.Dialog.DayDialog;
import com.example.litness.litness.Dialog.ImageDialog;
import com.example.litness.litness.Dialog.LoginDialog;
import com.example.litness.litness.Dialog.MenuDialog;
import com.example.litness.litness.Dialog.ReviewDialog;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BarDisplayActivity extends AppCompatActivity {

    public Bar b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_display);

        b = Client.activeBar;

        refreshLayout();

        findViewById(R.id.bar_button_checkin).setOnClickListener(v-> {
            //make them login if they aren't
            if(Client.currentUserName.equals(""))
                new LoginDialog(this, x-> {
                    new CheckInDialog(this, this::updateLitness).show();
                    Client.currentUserName = x;
                }).show();
            else
                new CheckInDialog(this, this::updateLitness).show();
        } );

        findViewById(R.id.bar_alt_phone).setOnClickListener(v-> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ b.phone))));

        findViewById(R.id.bar_alt_address).setOnClickListener(v-> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(b.address) + b.barName))));

        findViewById(R.id.bar_button_allspecials).setOnClickListener(v-> new DayDialog(this).show());

        //Only set the listeners if things will exist
        if(b.menu.food.size() != 0 || b.menu.drinks.size() != 0)
            findViewById(R.id.bar_card_menu).setOnClickListener(v-> new MenuDialog(this).show());

        if(b.livePhotos.size() != 0)
            findViewById(R.id.bar_card_live_photos).setOnClickListener(v-> new ImageDialog(this, b.livePhotos, 0).show());

        if(b.reviews.size() != 0)
            findViewById(R.id.bar_card_reviews).setOnClickListener(v-> new ReviewDialog(this).show());
    }

    private void refreshLayout() {
        Toolbar toolbar = findViewById(R.id.bar_toolbar);
        ((TextView) toolbar.findViewById(R.id.bar_title)).setText(b.barName);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        //Set up the photos up top
        setPhotos();

        setLitness();

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
        ((TextView) findViewById(R.id.bar_alt_rating)).setText(b.rating);

        //make sure there is an under cover
        if(b.coverUnder.length() < 2) {
            findViewById(R.id.bar_alt_cover_under).setVisibility(View.GONE);
            findViewById(R.id.textView13).setVisibility(View.GONE);
        }

        //Set the day of the week for Event and Specials
        ((TextView) findViewById(R.id.bar_alt_day)).setText(String.format("%sS", android.text.format.DateFormat.format("EEEE", new Date())));

        //get all the events for the day
        setEventsAndSpecials();

        //set description card to null if its null
        if(b.description.equals(""))
            findViewById(R.id.bar_card_description).setVisibility(View.GONE);

        //if there is no menu for this place
        if(b.menu.food.size() == 0 && b.menu.drinks.size() == 0)
            ((TextView) findViewById(R.id.bar_alt_menu)).setText("No Menu");

        if(b.livePhotos.size() == 0)
            ((TextView) findViewById(R.id.bar_alt_livephotos)).setText("No Live Photos");

        if(b.reviews.size() == 0) {
            findViewById(R.id.bar_layout_rating).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.bar_alt_reviews)).setText("No Reviews");
        }

    }

    private void setPhotos(){
        LinearLayout pics = findViewById(R.id.bar_gallery);
        for(int i = 0; i < b.photos.size(); i++) {
            int layout = R.layout.adapter_pics;
            View v = getLayoutInflater().inflate(layout, null, false);
            ImageView img = v.findViewById(R.id.adapter_image);
            img.setImageResource(b.photos.get(i));
            int index = i;
            img.setOnClickListener(x-> new ImageDialog(this,b.photos, index).show());
            pics.addView(v);
        }
    }

    private void setLitness() {
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

    private void setEventsAndSpecials() {
        //I just set to zero for easy loading
        if(b.days.get(0/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1*/) != null) {
            LinearLayout events = findViewById(R.id.bar_events);
            for (String cat : b.days.get(0)/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1]*/.events) {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_events, null, false);
                ((TextView) v.findViewById(R.id.adapter_alt_event)).setText(cat);
                events.addView(v);
            }
            LinearLayout specials = findViewById(R.id.bar_specials);
            for (String cat : b.days.get(0/*(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) - 1*/).specials) {
                @SuppressLint("InflateParams") View v = LayoutInflater.from(this).inflate(R.layout.adapter_specials, null, false);
                ((TextView) v.findViewById(R.id.adapter_alt_special)).setText(cat);
                specials.addView(v);
            }
        }
        //make the events and special bar gone if there are none
        else
            findViewById(R.id.bar_card_specials).setVisibility(View.GONE);
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
