package com.example.finalapp03;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;

public class Activity3 extends AppCompatActivity {

    // Constante Contenido Actividad
    private static int MICROPHONE_PERMISSION_CODE = 200;
    private final static int CONT_ACTIVIDAD = 2;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;

    private DrawerLayout drawerLayout;
    private MediaRecorder grabador;
    private MediaPlayer reproductor;
    private AudioManager manager;
    private String archivoSalida = null;
    private boolean hayAudio = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Componente ViewFlipper
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);
        vf.setDisplayedChild(CONT_ACTIVIDAD);

        // Componente NavigationDrawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(item -> {
            Intent sendIntent;
            switch (item.getItemId()) {
                case R.id.nav_item_one:
                    // Se inicia Actividad 1
                    sendIntent = new Intent(Activity3.this, MainActivity.class);
                    sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    onBackPressed();
                    startActivity(sendIntent);
                    break;
                case R.id.nav_item_two:
                    // Se inicia Actividad 2
                    sendIntent = new Intent(Activity3.this, Activity2.class);
                    sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    onBackPressed();
                    startActivity(sendIntent);
                    break;
                case R.id.nav_item_three:
                    // Se inicia Actividad 3
                    sendIntent = new Intent(Activity3.this, Activity3.class);
                    sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    onBackPressed();
                    startActivity(sendIntent);
                    break;
                case R.id.nav_item_four:
                    // Se inicia Actividad 4
                    sendIntent = new Intent(Activity3.this, Activity4.class);
                    sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    onBackPressed();
                    startActivity(sendIntent);
                    break;
            }
            // Close the navigation drawer when an item is selected
            drawerLayout.closeDrawers();
            return true;
        });

        if(microfono()){
            getPermisosMicrofono();
        }

        manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolumen = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int actualVolumen = manager.getStreamVolume(AudioManager.STREAM_MUSIC);

        Button btnGrabar = findViewById(R.id.btn_grabar);
        Button btnPausar = findViewById(R.id.btn_pausar);
        Button btnReproducir = findViewById(R.id.btn_reproducir);

        btnGrabar.setOnClickListener(v -> grabarAudio());
        btnReproducir.setOnClickListener(v -> reproducirAudio());
        btnPausar.setOnClickListener(v -> detenerGrabacion());

        // Obtener referencias a las seekbars
        SeekBar volumeSeekBar = findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setMax(maxVolumen);
        volumeSeekBar.setProgress(actualVolumen);

        SeekBar frequencySeekBar = findViewById(R.id.frequencySeekBar);

        // Establecer los escuchadores
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = (float) progress / 100.0f;
                reproductor.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        frequencySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                float speed = (float) progress / 10.0f;
                PlaybackParams params = new PlaybackParams();
                params.setSpeed(speed);
                reproductor.setPlaybackParams(params);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LastActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean microfono() {
        return this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }

    private void getPermisosMicrofono() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    private String getRecordingFilePath() {
        File file = new File((new ContextWrapper(getApplicationContext())
                .getExternalFilesDir(Environment.DIRECTORY_MUSIC)), "grabacion.mp3");
        return file.getPath();
    }

    ;

    private void grabarAudio() {
        if (grabador == null) {
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.mp4";
            grabador = new MediaRecorder();
            grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabador.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabador.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            grabador.setOutputFile(getRecordingFilePath());
            try {
                grabador.prepare();
                grabador.start();

                Toast.makeText(this, "Grabando..", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            detenerGrabacion();
        }
    }

    private void detenerGrabacion() {
        if (grabador != null) {
            grabador.stop();
            grabador.release();
            grabador = null;
            Toast.makeText(this, "Grabacion finalizada:)", Toast.LENGTH_SHORT).show();
            hayAudio = true;
        }
    }

    private void reproducirAudio() {
        if (hayAudio) {
            try {
                reproductor = new MediaPlayer();
                reproductor.setDataSource(getRecordingFilePath());
                reproductor.prepare();
                reproductor.start();
                Toast.makeText(this, "Reproduciendo grabación", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
