package com.app.alcheringa2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alcheringa2020.authentication.SharedPrefManager;

public class ContactUs extends AppCompatActivity {
    LinearLayout hemant, shashank, kartikey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        hemant = findViewById(R.id.hemant);
        shashank = findViewById(R.id.shashank);
        kartikey = findViewById(R.id.kartikey);

        hemant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:07663929177"));
                startActivity(intent);
            }
        });
        shashank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:07896658640"));
                startActivity(intent);

            }
        });
        kartikey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:07578062409"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    private void exitApp() {
//        Toast.makeText(this, "BACK", Toast.LENGTH_SHORT).show();
        try{
            SharedPrefManager.getInstance(this).fragmentwhere("support");}catch (Exception e){e.printStackTrace();}
        startActivity(new Intent(this,MainActivity.class));
//        Toast.makeText(this, ""+SharedPrefManager.getInstance(this).getFragmentName(), Toast.LENGTH_SHORT).show();
    }

    public void backOption(View view) {
        exitApp();
    }

}
