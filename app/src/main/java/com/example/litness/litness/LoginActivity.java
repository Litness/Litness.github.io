package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.litness.litness.Dialog.InputDialog;
import com.example.litness.litness.Dialog.RegisterDialog;

public class LoginActivity extends AppCompatActivity {

    private String inputEmail;
    private String inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button_login).setOnClickListener(v -> actionAdminLogin(((EditText) findViewById(R.id.login_input_email)).getText().toString(), ((EditText) findViewById(R.id.login_input_password)).getText().toString()));

        findViewById(R.id.login_button_forgot).setOnClickListener(v-> {
            InputDialog d = new InputDialog(this,"Email", null);
            d.setCancelable(false);
            d.show();
        });

        //auto sign them in
        findViewById(R.id.login_button_register).setOnClickListener(v -> {
            RegisterDialog d = new RegisterDialog(this, x-> actionAdminLogin(x.get(0),x.get(1)));
            d.setCancelable(false);
            d.show();
        } );
    }

    private void actionAdminLogin(String inputEmail, String inputPassword){
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
