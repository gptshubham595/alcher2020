package com.app.alcheringa2020.notification;

import android.app.Notification;
import android.content.Context;

import com.app.alcheringa2020.external.CommonFunctions;
import com.app.alcheringa2020.notification.model.NotiDetailModel;
import com.app.alcheringa2020.notification.model.NotificationModel;
import com.app.alcheringa2020.schedule.model.EventModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class NotificationDataModel {
    public static ArrayList<NotificationModel> notificationModelArrayList(Context context) {
        ArrayList<NotificationModel> notificationModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = CommonFunctions.loadAssetsJsonObj("notification.json", context);
            JSONArray notificationJsonArray = jsonObject.getJSONArray("notification");
            for (int i = 0; i < notificationJsonArray.length(); i++) {
                JSONObject notificationJsonObject = notificationJsonArray.getJSONObject(i);
                int id = notificationJsonObject.getInt("id");
                String day = notificationJsonObject.getString("day");
                String date = notificationJsonObject.getString("date");
                ArrayList<NotiDetailModel> notiDetailModelArrayList = new ArrayList<>();
                JSONArray notiArray = notificationJsonObject.getJSONArray("notification_detail");
                for (int j = 0; j < notiArray.length(); j++) {
                    JSONObject notidetailObject = notiArray.getJSONObject(j);
                    int notiId = notidetailObject.getInt("id");
                    String message = notidetailObject.getString("message");
                    notiDetailModelArrayList.add(new NotiDetailModel(notiId, message,"dance"));
                }
                notificationModelArrayList.add(new NotificationModel(id, day, date, notiDetailModelArrayList));

            }
            return notificationModelArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return notificationModelArrayList;
    }
}
