package com.app.alcheringa2020.external;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class PrefManager {
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "theme";
    private String THEME_VALUE = "theme_value";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean setTheme(boolean data) {
        editor.putBoolean(THEME_VALUE, data);
        editor.commit();
        return data;
    }

    public boolean getTheme() {
        return pref.getBoolean(THEME_VALUE, false);
    }
}
