package com.shubham.xamppsql;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);

        navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulog, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {

            case R.id.action_logout:
                startActivity(new Intent(this, LoginActivity.class));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                return true;

            case R.id.action_events:
                startActivity(new Intent(this, Events.class));
                return true;

            case R.id.action_teams:
                startActivity(new Intent(this, Teams.class));
                return true;

            case R.id.action_sponsers:
                startActivity(new Intent(this, Sponsers.class));
                return true;

            case R.id.action_faq:
                startActivity(new Intent(this, FAQ.class));
                return true;

            case R.id.action_about:
                startActivity(new Intent(this, About.class));
                return true;

            case R.id.action_logout:
                startActivity(new Intent(this, LoginActivity.class));
                return true;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}