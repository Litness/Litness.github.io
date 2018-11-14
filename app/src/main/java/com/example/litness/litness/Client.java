package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.HashMap;

public class Client extends AppCompatActivity {

    public static String currentUserName;
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
        Bar bar = new Bar("Rounders", "$10", "10 Minutes", "Music", "2");
        barMap.put(bar.getBarName(),bar);
    }

    private void launchFirstActivity() {
/*        if (preferenceFileExist("Login"))
            getLogin();
        else {*/
            startActivity(new Intent(this,LoginActivity.class));
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
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
