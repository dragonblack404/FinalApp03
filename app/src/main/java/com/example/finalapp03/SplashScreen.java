package com.example.finalapp03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

//    VideoView video = findViewById(R.id.videoView);

    // Constante de tiempo de duración para el Splash Screen
    final static int TIEMPO = 2300;

    // Carga el archivo de vídeo desde los recursos
//    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_background);

//        video.setVideoURI(uri);
//        video.start();

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