package com.example.litness.litness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView tvPassword = findViewById(R.id.label_editprofile_password);
        TextView tvConfirm = findViewById(R.id.label_editprofile__confirm);
        EditText etPassword = findViewById(R.id.input_editprofile__password);
        EditText etConfirm = findViewById(R.id.input_editprofile__confirm);

        Button buttonUpdate = findViewById(R.id.button_editprofile_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                actionUpdate();
            }
        });
    }

    private void actionUpdate() {
        EditText eEmail = findViewById(R.id.input_editprofile_email);
        EditText eName = findViewById(R.id.input_editprofile__name);
        EditText ePassword = findViewById(R.id.input_editprofile__password);
        EditText eConfirm = findViewById(R.id.input_editprofile__confirm);

        String sEmail = eEmail.getText().toString();
        String sName = eName.getText().toString();
        String sPassword = ePassword.getText().toString();
        String sConfirm = eConfirm.getText().toString();

        if (sEmail.equals("")) {
            StaticUtilities.showSimpleAlert(this, "Blank Field", "You must provide an email");

        }
        else if(sName.equals("")) {
            StaticUtilities.showSimpleAlert(this, "Blank Field", "You must provide a name.");
        }
        else if(!sPassword.equals(sConfirm)){
            StaticUtilities.showSimpleAlert(this, "Password Mismatch", "Passwords do not match.");
        }
        else {
            saveLoginInfo(sEmail,sPassword);
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    private void saveLoginInfo(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Unm",email);
        editor.putString("Psw",password);
        editor.apply();
    }
}
