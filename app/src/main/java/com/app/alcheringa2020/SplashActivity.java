package com.app.alcheringa2020;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import com.app.alcheringa2020.authentication.FBLogin;
import com.app.alcheringa2020.authentication.LoginActivity;
import com.app.alcheringa2020.base.BaseActivity;
import com.google.firebase.FirebaseApp;

import maes.tech.intentanim.CustomIntent;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    mainMethod();

            }
        }, 3000);
    }

    private void mainMethod() {
//        startActivity(new Intent(this, LoginActivity.class));
                startActivity(new Intent(this, LoginActivity.class));
                CustomIntent.customType(SplashActivity.this,"fadein-to-fadeout");
    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.d("", "Permission is granted");
                return true;
            } else {
                Log.d("", "Permission is revoked");
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        1
                );
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.d("", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("", "Permission is granted");
            mainMethod();
        }
    }

}
