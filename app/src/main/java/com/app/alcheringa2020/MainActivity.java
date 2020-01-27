package com.app.alcheringa2020;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.alcheringa2020.authentication.ProfileActivity;
import com.app.alcheringa2020.base.BaseActivity;
import com.app.alcheringa2020.events.EventsFragment;
import com.app.alcheringa2020.feed.FeedFragment;
import com.app.alcheringa2020.map.MapList;
import com.app.alcheringa2020.notification.NotificationFragment;
import com.app.alcheringa2020.schedule.MyScheduleFragment;
import com.app.alcheringa2020.schedule.ScheduleFragment;
import com.app.alcheringa2020.search.SearchActivity;
import com.app.alcheringa2020.support.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    String TAG = MainActivity.class.getSimpleName();
    Fragment fragment;
    String currentFragment;
    BottomNavigationView navigationView;
    public static ImageView main_logo, noti_image, back_image, search_image, add_image, fav_image;
    public static TextView nav_title;
    public static RelativeLayout noti_rlt;

    public static CircleImageView profile_image;

    FirebaseFirestore db;
    DatabaseReference reference;
    FloatingActionButton floatingActionButton;
    public static final String CHANNEL_ID = "mohan";
    private static final String CHANNEL_NAME = "mohan";
    private static final String CHANNEL_DESC = "mohan notification";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        db = FirebaseFirestore.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("mohan");

        FirebaseMessaging.getInstance().subscribeToTopic("music");
        FirebaseMessaging.getInstance().subscribeToTopic("game");
        FirebaseMessaging.getInstance().subscribeToTopic("dance");
        FirebaseMessaging.getInstance().subscribeToTopic("fineart");
        FirebaseMessaging.getInstance().subscribeToTopic("general");
        floatingActionButton = findViewById(R.id.map_floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapList.class));
            }
        });

        search_image = findViewById(R.id.search_image);
        back_image = findViewById(R.id.back_image);
        noti_image = findViewById(R.id.noti_image);
        profile_image = findViewById(R.id.profile_image);
        main_logo = findViewById(R.id.main_logo);
        fav_image = findViewById(R.id.fav_image);
        nav_title = findViewById(R.id.nav_title);
        noti_rlt = findViewById(R.id.noti_rlt);
        navigationView = findViewById(R.id.bottomNavigationView);
        add_image = findViewById(R.id.add_image);
        navigationView.setOnNavigationItemSelectedListener(this);
        fragment = FeedFragment.newInstance(context);
        currentFragment = "FeedFragment";
        setFragment(fragment);
        initListner();

        String img = "";
        try {
            img = getIntent().getStringExtra("img_url");

            final String finalImg = img;

            if (isStoragePermissionGranted()) {
                //                    Uri imgUri = Uri.parse("/storage/emulated/0/alcher2020/profile.jpg");
                File imgFile = new File(Environment.getExternalStorageDirectory() + "/alcher.png");
//                    Toast.makeText(this, ""+imgFile, Toast.LENGTH_SHORT).show();
                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    profile_image.setImageBitmap(myBitmap);

                } else {
                    if(!img.equals(""))
                    {Picasso.get().load(img).placeholder(R.drawable.profile).networkPolicy(NetworkPolicy.OFFLINE).into(profile_image, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get().load(finalImg).placeholder(R.drawable.profile).into(profile_image);
                            Bitmap bitmap = ((BitmapDrawable)profile_image.getDrawable()).getBitmap();
                            saveBitmap(bitmap);
                        }
                    });

                    }
                    else{

                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
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
            Toast.makeText(this, "SAVED at " + imagepath, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    private void initListner() {
        try {
            noti_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = NotificationFragment.newInstance(context);
                    currentFragment = "NotificationFragment";
                    setFragment(fragment);
                    nav_title.setText(R.string.notifications);
                    showHide();
                }
            });
            search_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SearchActivity.class);
                    startActivity(intent);
                }
            });
            profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    fragment = ProfileFragment.newInstance(context);
//                    currentFragment = "ProfileFragment";
//                    setFragment(fragment);
//                    nav_title.setText(R.string.profile);
//                    showHide();
//                    finish();
                    Intent intent = new Intent(context, ProfileActivity.class);
                    startActivity(intent);
                }
            });
            fav_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = MyScheduleFragment.newInstance(context);
                    currentFragment = "MyScheduleFragment";
                    setFragment(fragment);
                    nav_title.setText("My Schedule");
                    showHide();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d(TAG, "item id: " + menuItem.getItemId());
        switch (menuItem.getItemId()) {
            case R.id.nav_feed:
                feedFragment();
                break;
            case R.id.nav_events:
                fragment = EventsFragment.newInstance(context);
                currentFragment = "EventsFragment";
                setFragment(fragment);
                nav_title.setText(R.string.events);
                showHide();
                break;
            case R.id.nav_schedule:
                fragment = ScheduleFragment.newInstance(context);
                currentFragment = "ScheduleFragment";
                setFragment(fragment);
                nav_title.setText(R.string.schedule);
                showHide();
                break;
            case R.id.nav_support:
                fragment = SupportFragment.newInstance(context);
                currentFragment = "SupportFragment";
                setFragment(fragment);
                nav_title.setText(R.string.support);
                showHide();
                break;
        }
        return false;
    }

    private void feedFragment() {
        fragment = FeedFragment.newInstance(context);
        currentFragment = "FeedFragment";
        main_logo.setVisibility(View.VISIBLE);
        back_image.setVisibility(View.GONE);
        noti_image.setVisibility(View.VISIBLE);
        nav_title.setVisibility(View.GONE);
        profile_image.setVisibility(View.VISIBLE);
        search_image.setVisibility(View.GONE);
        add_image.setVisibility(View.GONE);
        noti_rlt.setVisibility(View.VISIBLE);
        setFragment(fragment);
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
                return false;
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

    private void showHide() {
        profile_image.setVisibility(View.GONE);
        main_logo.setVisibility(View.GONE);
        noti_image.setVisibility(View.GONE);
        back_image.setVisibility(View.VISIBLE);
        nav_title.setVisibility(View.VISIBLE);
        if (currentFragment.equalsIgnoreCase("EventsFragment") || currentFragment.equalsIgnoreCase("NotificationFragment")) {
            search_image.setVisibility(View.VISIBLE);
            noti_image.setVisibility(View.GONE);
        } else {
            search_image.setVisibility(View.GONE);
            noti_image.setVisibility(View.VISIBLE);
        }

        if (currentFragment.equalsIgnoreCase("TeamFragment")) {
            add_image.setVisibility(View.VISIBLE);
            noti_image.setVisibility(View.GONE);
        } else {
            add_image.setVisibility(View.GONE);
            noti_image.setVisibility(View.VISIBLE);
        }
        if (currentFragment.equalsIgnoreCase("ProfileFragment") || currentFragment.equalsIgnoreCase("MyScheduleFragment") || currentFragment.equalsIgnoreCase("SupportFragment")) {
            noti_rlt.setVisibility(View.INVISIBLE);
            noti_image.setVisibility(View.VISIBLE);
        } else {
            noti_rlt.setVisibility(View.VISIBLE);
            noti_image.setVisibility(View.GONE);
        }

        if (currentFragment.equalsIgnoreCase("ScheduleFragment")) {
            fav_image.setVisibility(View.VISIBLE);
            noti_image.setVisibility(View.GONE);
        } else {
            fav_image.setVisibility(View.GONE);
            noti_image.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {
        exitApp();
    }

    private void exitApp() {
        if (currentFragment.equalsIgnoreCase("FeedFragment")) {
            try {
                new AlertDialog.Builder(this)
                        .setTitle("Confirm")
                        .setIcon(R.drawable.logo_dark)
                        .setMessage("Do you really want to exit?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            feedFragment();
        }
    }

    public void backOption(View view) {
        exitApp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        feedFragment();
    }
}
