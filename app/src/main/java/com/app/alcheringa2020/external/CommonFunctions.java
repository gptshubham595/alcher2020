package com.app.alcheringa2020.external;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class CommonFunctions {
    private static String TAG = CommonFunctions.class.getSimpleName();

    public static JSONObject loadAssetsJsonObj(String fileName, Context context) {
        String json = null;
        JSONObject array = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                array = new JSONObject(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return array;

    }

    public static boolean isInternetOn(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean NisConnected = activeNetwork != null && activeNetwork.isConnected();
        if (NisConnected) {
            //  if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE || activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
            else
                return false;
        }
        return false;
    }


    public static void loadAnimation(View view){
        YoYo.with(Techniques.ZoomIn)
                .duration(400)
                .repeat(0)
                .playOn(view);
        //view.animation = AnimationUtils.loadAnimation(view.context, R.anim.popup_show)
    }

}