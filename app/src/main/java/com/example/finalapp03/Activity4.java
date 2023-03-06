package com.example.finalapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.example.finalapp03.fragments.FragBateria;
import com.example.finalapp03.fragments.FragLocation;
import com.example.finalapp03.fragments.FragRes;
import com.example.finalapp03.fragments.FragSensor;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import com.example.finalapp03.adapters.*;

public class Activity4 extends AppCompatActivity {

    // Constante Contenido Actividad
    private final static int CONT_ACTIVIDAD = 3;

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Componente ViewFlipper
        vf = (ViewFlipper) findViewById(R.id.vf);
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
                        sendIntent = new Intent(Activity4.this, MainActivity.class);
                        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_two:
                        // Se inicia Actividad 2
                        sendIntent = new Intent(Activity4.this, Activity2.class);
                        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_three:
                        // Se inicia Actividad 3
                        sendIntent = new Intent(Activity4.this, Activity3.class);
                        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_four:
                        // Se inicia Actividad 3
                        sendIntent = new Intent(Activity4.this, Activity4.class);
                        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                }
                // Close the navigation drawer when an item is selected
                drawerLayout.closeDrawers();
                return true;
            }
        });

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        // Creamos un adaptador para el ViewPager
        Activity4Adapter adapter = new Activity4Adapter(getSupportFragmentManager());

        // Agregamos los fragmentos al adaptador
        adapter.addFragment(new FragRes(), getString(R.string.tab1));
        adapter.addFragment(new FragBateria(), getString(R.string.tab2));
        adapter.addFragment(new FragSensor(), getString(R.string.tab3));
        adapter.addFragment(new FragLocation(), getString(R.string.tab4));

        // Asociamos el adaptador al ViewPager
        viewPager.setAdapter(adapter);

        // Asociamos el ViewPager al TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LastActivity.class);
        startActivity(intent);
        finish();
    }

}
