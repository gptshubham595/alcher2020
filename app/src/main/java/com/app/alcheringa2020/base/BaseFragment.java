package com.app.alcheringa2020.base;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.external.PrefManager;

/**
 * Created by Jiaur Rahman on 27-Dec-19.
 */
public class BaseFragment extends Fragment {
    public static Context context;
    public static View view;
    Fragment fragment1;
    public static PrefManager prefManager;
    boolean isLight = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setAppTheme();
        super.onCreate(savedInstanceState);

    }


    private void setAppTheme() {
        context = getActivity();
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
            context.setTheme(R.style.AppTheme);
        } else {
            context.setTheme(R.style.LightAppTheme);
        }
    }
}
