package com.app.alcheringa2020;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.alcheringa2020.authentication.ProfileActivity;
import com.app.alcheringa2020.base.BaseActivity;
import com.app.alcheringa2020.events.EventsFragment;
import com.app.alcheringa2020.external.PrefManager;
import com.app.alcheringa2020.feed.FeedFragment;
import com.app.alcheringa2020.map.MapList;
import com.app.alcheringa2020.notification.NotificationFragment;
import com.app.alcheringa2020.profile.ProfileFragment;
import com.app.alcheringa2020.schedule.MyScheduleFragment;
import com.app.alcheringa2020.schedule.ScheduleFragment;
import com.app.alcheringa2020.search.SearchActivity;
import com.app.alcheringa2020.support.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    String TAG = MainActivity.class.getSimpleName();
    Fragment fragment;
    String currentFragment;
    BottomNavigationView navigationView;
    public static ImageView main_logo, noti_image, profile_image, back_image, search_image, add_image, fav_image;
    public static TextView nav_title;
    public static RelativeLayout noti_rlt;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("mohan");
    FloatingActionButton floatingActionButton;
    public static final String CHANNEL_ID = "mohan";
    private static final String CHANNEL_NAME = "mohan";
    private static final String CHANNEL_DESC = "mohan notification";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
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
                    finish();
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

    private void showHide() {
        profile_image.setVisibility(View.GONE);
        main_logo.setVisibility(View.GONE);
        noti_image.setVisibility(View.GONE);
        back_image.setVisibility(View.VISIBLE);
        nav_title.setVisibility(View.VISIBLE);
        if (currentFragment.equalsIgnoreCase("EventsFragment") || currentFragment.equalsIgnoreCase("NotificationFragment")) {
            search_image.setVisibility(View.VISIBLE);
        } else {
            search_image.setVisibility(View.GONE);
        }

        if (currentFragment.equalsIgnoreCase("TeamFragment")) {
            add_image.setVisibility(View.VISIBLE);
        } else {
            add_image.setVisibility(View.GONE);
        }
        if (currentFragment.equalsIgnoreCase("ProfileFragment") || currentFragment.equalsIgnoreCase("MyScheduleFragment") || currentFragment.equalsIgnoreCase("SupportFragment")) {
            noti_rlt.setVisibility(View.INVISIBLE);
        } else {
            noti_rlt.setVisibility(View.VISIBLE);
        }

        if (currentFragment.equalsIgnoreCase("ScheduleFragment")) {
            fav_image.setVisibility(View.VISIBLE);
        } else {
            fav_image.setVisibility(View.GONE);
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
