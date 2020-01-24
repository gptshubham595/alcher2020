package com.app.alcheringa2020.external;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.multidex.MultiDex;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class MainController extends Application {
    private static MainController appInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;
    //private static Context mContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        appInstance = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //sharedPreferencesEditor = sharedPreferences.edit();
        //setContext(getApplicationContext());
        MultiDex.install(this);
    }

    public static MainController getAppInstance() {
        if (appInstance == null)
            throw new IllegalStateException("The application is not created yet!");
        return appInstance;
    }

    public static SharedPreferences.Editor getApplicationPreferenceEditor() {
        if (sharedPreferencesEditor == null)
            sharedPreferencesEditor = sharedPreferences.edit();

        return sharedPreferencesEditor;
    }

    public static SharedPreferences getApplicationPreference() {
        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appInstance);

        return sharedPreferences;
    }
}
