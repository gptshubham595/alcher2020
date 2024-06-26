package com.app.alcheringa2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.alcheringa2020.authentication.SharedPrefManager;

public class Developer extends AppCompatActivity {
    LinearLayout shubham, mohan, siddharth,priyanshu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        shubham = findViewById(R.id.shubham);
        mohan = findViewById(R.id.mohan);
        siddharth=findViewById(R.id.siddharth);
        priyanshu=findViewById(R.id.priyanshu);

        shubham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/gptshubham595"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        mohan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/mohan.kr.3720"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        priyanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/priyanshu.jain.393"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        siddharth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/Siddharthjain2424"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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
