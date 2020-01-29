package com.app.alcheringa2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

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
}
