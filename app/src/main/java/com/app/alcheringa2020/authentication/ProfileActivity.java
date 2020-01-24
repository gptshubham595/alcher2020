package com.app.alcheringa2020.authentication;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.app.alcheringa2020.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {



    private TextView textViewUsername, textViewUserEmail;
    private RelativeLayout signout;
    ImageView edit_profile;
    CircleImageView profile_image;
    private int RESULT_LOAD_IMAGE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        signout = findViewById(R.id.signout);
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUseremail);
        edit_profile=findViewById(R.id.edit_profile);
        profile_image=findViewById(R.id.profile_image);


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStoragePermissionGranted()){
                    Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i,RESULT_LOAD_IMAGE);
                }
            }
        });

        if(isStoragePermissionGranted()) {
            Uri imgUri = Uri.parse("/storage/emulated/0/alcher2020/profile.jpg");
            profile_image.setImageURI(imgUri);
        }

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(ProfileActivity.this).logout();
                finish();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });
        textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textViewUsername.setText(SharedPrefManager.getInstance(this).getUsername());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data!=null){
            Uri selectedimage=data.getData();
            profile_image.setImageURI(selectedimage);

            profile_image.invalidate();
            BitmapDrawable drawable = (BitmapDrawable) profile_image.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File file = new File(directory, "profile" + ".jpg");
            if (!file.exists()) {
                Log.d("path", file.toString());
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("TAG","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
}
