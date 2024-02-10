package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.User.angeldochealthcare.MainActivity;
import com.User.angeldochealthcare.R;


public class Splash extends AppCompatActivity {
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (token!=null)
                {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(Splash.this, LoginPage.class));
                    finish();
                }
            }
        },3000);
    }
}