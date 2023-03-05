package com.example.finalapp03;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    // Constante de tiempo de duración para el Splash Screen
    final static int TIEMPO = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_background);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, TIEMPO);
    }
}