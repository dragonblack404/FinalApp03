package com.example.finalapp03;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalapp03.db.DB;
import com.example.finalapp03.items.Item;
import com.example.finalapp03.items.ItemAdapter;
import com.example.finalapp03.model.Articulo;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Constante Contenido Actividad
    private final static int CONT_ACTIVIDAD = 0;

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;
    private RecyclerView recyclerView;
    private DB db;
    private ArrayList<Articulo> itemList = new ArrayList<>();

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
                        sendIntent = new Intent(MainActivity.this, MainActivity.class);
                        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_two:
                        // Se inicia Actividad 2
                        sendIntent = new Intent(MainActivity.this, Activity2.class);
                        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_three:
                        // Se inicia Actividad 3
                        sendIntent = new Intent(MainActivity.this, Activity3.class);
                        sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_four:
                        // Se inicia Actividad 4
                        sendIntent = new Intent(MainActivity.this, Activity4.class);
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

//    db.onCreate();

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LastActivity.class);
        startActivity(intent);
        finish();
    }
}