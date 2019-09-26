package com.webops.alcheringa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class About extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        drawerLayout =findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView=findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //startActivity(i);
            }
        }, 2000);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulog, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){

            case R.id.action_logout:startActivity(new Intent(this, Login.class)); return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:startActivity(new Intent(this, MainActivity.class)); return true;

            case R.id.action_events:startActivity(new Intent(this, Events.class)); return true;

            case R.id.action_teams:startActivity(new Intent(this, Teams.class)); return true;

            case R.id.action_sponsers:startActivity(new Intent(this, Sponsers.class)); return true;

            case R.id.action_faq:startActivity(new Intent(this, FAQ.class)); return true;

            case R.id.action_about:return true;

            case R.id.action_logout:startActivity(new Intent(this, Login.class)); return true;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
