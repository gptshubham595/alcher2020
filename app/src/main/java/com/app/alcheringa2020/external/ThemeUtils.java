package com.app.alcheringa2020.external;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.app.alcheringa2020.R;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class ThemeUtils {
    private static boolean cTheme;

    /*public static void changeToTheme(Activity activity, boolean theme) {
        cTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }*/
    public ThemeUtils(boolean theme, Context context) {
        cTheme = theme;

        if (cTheme) {
            context.setTheme(R.style.LightAppTheme);

        } else {
            context.setTheme(R.style.AppTheme);
        }
        Log.d("theme utils", "theme color: " + cTheme);
    }

    public static void onActivityCreateSetTheme(boolean theme, Context context) {
        cTheme = theme;

        if (cTheme) {
            context.setTheme(R.style.LightAppTheme);

        } else {
            context.setTheme(R.style.AppTheme);
        }
        Log.d("theme utils", "theme color: " + cTheme);
    }
}
