package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private String inputEmail;
    private String inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.button_to_main_activity);
        buttonLogin.setOnClickListener(v -> actionAdminLogin());

        Button buttonToNewGroup = findViewById(R.id.button_to_register);
        buttonToNewGroup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)) );
    }

    private void actionAdminLogin(){
        inputEmail = ((EditText) findViewById(R.id.input_email)).getText().toString();
        inputPassword = ((EditText) findViewById(R.id.input_password)).getText().toString();

        saveLoginInfo(inputEmail,inputPassword);
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
}
