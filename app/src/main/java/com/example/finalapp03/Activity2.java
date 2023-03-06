package com.example.finalapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.example.finalapp03.items.Item;
import com.example.finalapp03.items.ItemAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    // Constante Contenido Actividad
    private final static int CONT_ACTIVIDAD = 1;

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private ViewFlipper vf;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ArrayList<Item> itemList = new ArrayList<>();

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
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_two:
                        // Se inicia Actividad 2
                        sendIntent = new Intent(Activity2.this, Activity2.class);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_three:
                        // Se inicia Actividad 3
                        sendIntent = new Intent(Activity2.this, Activity3.class);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                    case R.id.nav_item_four:
                        // Se inicia Actividad 4
                        sendIntent = new Intent(Activity2.this, Activity4.class);
                        onBackPressed();
                        startActivity(sendIntent);
                        break;
                }
                // Close the navigation drawer when an item is selected
                drawerLayout.closeDrawers();
                return true;
            }
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList.add(new Item(getString(R.string.titulo_arma1), getString(R.string.desc_arma1), R.drawable.m16));
        itemList.add(new Item(getString(R.string.titulo_arma2), getString(R.string.desc_arma2), R.drawable.ak47));
        itemList.add(new Item(getString(R.string.titulo_arma3), getString(R.string.desc_arma3), R.drawable.m4a1));
        itemList.add(new Item(getString(R.string.titulo_arma4), getString(R.string.desc_arma4), R.drawable.franco));
        itemList.add(new Item(getString(R.string.titulo_arma5), getString(R.string.desc_arma5), R.drawable.g36));
        itemList.add(new Item(getString(R.string.titulo_arma6), getString(R.string.desc_arma6), R.drawable.model));
        itemList.add(new Item(getString(R.string.titulo_arma7), getString(R.string.desc_arma7), R.drawable.rpg));
        itemList.add(new Item(getString(R.string.titulo_arma8), getString(R.string.desc_arma8), R.drawable.colt));
        itemList.add(new Item(getString(R.string.titulo_arma9), getString(R.string.desc_arma9), R.drawable.ump));
        itemList.add(new Item(getString(R.string.titulo_arma10), getString(R.string.desc_arma10), R.drawable.aa12));

        itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LastActivity.class);
        startActivity(intent);
        finish();
    }

}