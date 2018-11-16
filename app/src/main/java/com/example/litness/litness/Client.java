package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.litness.litness.Bar.Day;
import com.example.litness.litness.Dialog.LoginDialog;

import java.io.File;
import java.util.HashMap;

public class Client extends AppCompatActivity {

    public static String currentUserName = "";
    public static HashMap<String, Bar> barMap;
    public static Bar activeBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        barMap = new HashMap<>();
        populateBarMap();

        launchFirstActivity();
        finish();
    }

    private void populateBarMap() {
        Bar bar = new Bar();
        bar.barName = "Rounders";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.litness = 1;
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.description = "This bar is a chill place with a lot of good beer and hot women";
        bar.tags.add("Night Clubs");
        bar.tags.add("All Bars");

        Day today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[4] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Calm Bar";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.tags.add("Bars with Food");
        bar.tags.add("All Bars");
        bar.litness = 2;

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[4] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Rounders1";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.litness = 3;
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.description = "This bar is a chill place with a lot of good beer and hot women";
        bar.tags.add("Night Clubs");
        bar.tags.add("All Bars");

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[4] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Calm Bar1";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.tags.add("Bars with Food");
        bar.tags.add("All Bars");
        bar.litness = 1;

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[4] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Rounders2";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.litness = 4;
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.description = "This bar is a chill place with a lot of good beer and hot women";
        bar.tags.add("Night Clubs");
        bar.tags.add("All Bars");

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[4] = today;

        barMap.put(bar.barName,bar);

        bar = new Bar();
        bar.barName = "Calm Bar2";
        bar.coverOver = "$10";
        bar.wait = "10 Minutes";
        bar.phone = "(205) 252-1213";
        bar.address = "12432 Test Street Tuscaloosa, AL 35404";
        bar.tags.add("Bars with Food");
        bar.tags.add("All Bars");
        bar.litness = 5;

        today = new Day();
        today.events.add("Test");
        today.specials.add("Test Pass");
        bar.days[4] = today;

        barMap.put(bar.barName,bar);
    }

    private void launchFirstActivity() {
/*        if (preferenceFileExist("Login"))
            getLogin();
        else {*/
            startActivity(new Intent(this,MainActivity.class));
            finish();
        //}

    }

    public boolean preferenceFileExist(String fileName) {
        return new File(getApplicationContext().getApplicationInfo().dataDir + "/shared_prefs/" + fileName + ".xml").exists();
    }

    private void getLogin() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", MODE_PRIVATE);
        String username = sharedPreferences.getString("Unm",null);
        String password = sharedPreferences.getString("Psw",null);
        checkLogin(username,password);
    }

    private void checkLogin(String username, String password) {
        //if the login is null
        if(username == null && password == null) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
