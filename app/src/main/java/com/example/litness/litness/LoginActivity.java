package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private String inputEmail;
    private String inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button buttonLogin = findViewById(R.id.button_to_main_activity);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                actionAdminLogin();
            }
        });

        final Button buttonToNewGroup = findViewById(R.id.button_to_register);
        buttonToNewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                actionGotoNewGroup();
            }
        });
    }

    private void actionAdminLogin(){
        StaticUtilities.hideSoftKeyboard(LoginActivity.this);
        inputEmail = ((EditText) findViewById(R.id.input_email)).getText().toString();
        inputPassword = ((EditText) findViewById(R.id.input_password)).getText().toString();

        saveLoginInfo(inputEmail,inputPassword); //no lol we can't store plaintext password
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void saveLoginInfo(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Unm",email);
        editor.putString("Psw",password);
        editor.apply();
    }

    private void actionGotoNewGroup(){
        StaticUtilities.hideSoftKeyboard(LoginActivity.this);
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
