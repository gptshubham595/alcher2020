package com.app.alcheringa2020.authentication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.app.alcheringa2020.MainActivity;
import com.app.alcheringa2020.R;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {


    private TextView textViewUsername, textViewUserEmail;
    private RelativeLayout signout;
    ImageView edit_profile;
    CircleImageView profile_image;
    private int RESULT_LOAD_IMAGE = 101;
    RelativeLayout gc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        gc=findViewById(R.id.gc);
        gc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.alcheringa.in/gc.html"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        signout = findViewById(R.id.signout);
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUseremail);
        edit_profile = findViewById(R.id.edit_profile);
        profile_image = findViewById(R.id.profile_image);

        try {
//            img = getIntent().getStringExtra("img_url");


            if (isStoragePermissionGranted()) {
                //                    Uri imgUri = Uri.parse("/storage/emulated/0/alcher2020/profile.jpg");
                File imgFile = new File(Environment.getExternalStorageDirectory() + "/alcher.png");
//                    Toast.makeText(this, ""+imgFile, Toast.LENGTH_SHORT).show();
                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    profile_image.setImageBitmap(myBitmap);

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted()) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            }
        });


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FacebookSdk.sdkInitialize(getApplicationContext());
                    LoginManager.getInstance().logOut();
                }catch (Exception e){e.printStackTrace();}
                SharedPrefManager.getInstance(ProfileActivity.this).logout();
                finish();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });
        textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        String username = SharedPrefManager.getInstance(this).getUsername();

        try {
            int index = username.indexOf('@');
            textViewUsername.setText(username.substring(0, index));
        } catch (Exception e) {
            e.printStackTrace();
            textViewUsername.setText(username);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedimage = data.getData();
            profile_image.setImageURI(selectedimage);

            profile_image.invalidate();
            BitmapDrawable drawable = (BitmapDrawable) profile_image.getDrawable();
            Bitmap bitmap = drawable.getBitmap();

            saveBitmap(bitmap);

        }
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagepath = new File(Environment.getExternalStorageDirectory() + "/alcher.png");
        FileOutputStream fos;
        String path;
        //File file=new File(path);
        try {
            fos = new FileOutputStream(imagepath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return true;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("TAG", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
