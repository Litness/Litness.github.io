package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonUpdate = findViewById(R.id.button_editprofile_update);
        buttonUpdate.setOnClickListener(v -> actionUpdate());
    }

    private void actionUpdate() {
        EditText email = findViewById(R.id.input_editprofile_email);
        EditText name = findViewById(R.id.input_editprofile__name);
        EditText password0 = findViewById(R.id.input_editprofile__password);
        EditText password1 = findViewById(R.id.input_editprofile__confirm);


        if (email.getText().toString().equals(""))
            email.setError("You must provide a email");
        else if(name.getText().toString().equals(""))
            name.setError("You must provide a name");
        else if(!password0.getText().toString().equals(password1.getText().toString()))
            password0.setError("Passwords must match");
        else {
            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Unm",email.getText().toString());
            editor.putString("Psw",password0.getText().toString());
            editor.apply();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
