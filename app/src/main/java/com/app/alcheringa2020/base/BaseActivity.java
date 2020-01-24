package com.app.alcheringa2020.base;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.external.PrefManager;

/**
 * Created by Jiaur Rahman on 27-Dec-19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Context context;
    PrefManager prefManager;
    boolean isLight = false;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setAppTheme();
        super.onCreate(savedInstanceState);

    }

    public void toolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setAppTheme() {
        context = getApplicationContext();
        prefManager = new PrefManager(context);
        isLight = prefManager.getTheme();
        Log.d("sasasa", "wwww: " + AppCompatDelegate.getDefaultNightMode());


        if (!isLight) {
            prefManager.setTheme(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            prefManager.setTheme(true);/*getResources().getColor(R.color.<name_of_color>)*/
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //getResources().


        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.LightAppTheme);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
