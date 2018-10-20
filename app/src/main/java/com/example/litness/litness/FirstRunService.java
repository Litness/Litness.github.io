package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.util.UUID;

public class FirstRunService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run_service);

        launchFirstActivity();
    }

    private void launchFirstActivity() {
        if (preferenceFileExist("Login")) {
            getLogin();
        }
        else {
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }

    }

    public boolean preferenceFileExist(String fileName) {
        File f = new File(getApplicationContext().getApplicationInfo().dataDir + "/shared_prefs/" + fileName + ".xml");
        return f.exists();
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
