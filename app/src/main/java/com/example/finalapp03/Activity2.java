package com.example.finalapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class Activity2 extends AppCompatActivity {

    // Constante Contenido Actividad
    private final static int CONT_ACTIVIDAD = 1;

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Componente ViewFlipper
        vf = (ViewFlipper)findViewById(R.id.vf);
        vf.setDisplayedChild(CONT_ACTIVIDAD);

        // Componente NavigationDrawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav = (NavigationView) findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent sendIntent;
                switch (item.getItemId()) {
                    case R.id.nav_item_one:
                        // Se inicia Actividad 1
                        sendIntent = new Intent(Activity2.this, MainActivity.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_two:
                        // Se inicia Actividad 2
                        sendIntent = new Intent(Activity2.this, Activity2.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_three:
                        // Se inicia Actividad 3
                        sendIntent = new Intent(Activity2.this, Activity3.class);
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_four:
                        // Se inicia Actividad 4
                        sendIntent = new Intent(Activity2.this, Activity4.class);
                        startActivity(sendIntent);
                        break;
                }
                // Close the navigation drawer when an item is selected
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}