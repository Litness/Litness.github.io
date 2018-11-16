package com.example.litness.litness.Dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;


import com.example.litness.litness.R;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class LoginDialog extends AlertDialog {
    Context ctx;

    public LoginDialog(Context c) {
        super(c);
        ctx = c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);

        Objects.requireNonNull((View) findViewById(R.id.login_button_login)).setOnClickListener(v -> {
            actionAdminLogin(((EditText) Objects.requireNonNull((View) findViewById(R.id.login_input_email))).getText().toString(), ((EditText) Objects.requireNonNull((View) findViewById(R.id.login_input_password))).getText().toString());
            dismiss();
        });

        Objects.requireNonNull((View) findViewById(R.id.login_button_forgot)).setOnClickListener(v-> {
            dismiss();
            InputDialog d = new InputDialog(ctx,"Email", x->
                new OkDialog(ctx,"", "A reset password link was sent to " + x, null).show());
            d.setCancelable(false);
            d.show();
        });

        //auto sign them in
        Objects.requireNonNull((View) findViewById(R.id.login_button_register)).setOnClickListener(v -> {
            dismiss();
            RegisterDialog d = new RegisterDialog(ctx, x-> actionAdminLogin(x.get(0),x.get(1)));
            d.setCancelable(false);
            d.show();
        } );

        Objects.requireNonNull((View) findViewById(R.id.login_button_close)).setOnClickListener(x -> dismiss());

    }

    private void actionAdminLogin(String inputEmail, String inputPassword){
/*        saveLoginInfo(inputEmail,inputPassword);
        startActivity(new Intent(ctx, MainActivity.class));
        finish();*/
    }

    private void saveLoginInfo(String email, String password) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Unm",email);
        editor.putString("Psw",password);
        editor.apply();
    }
}