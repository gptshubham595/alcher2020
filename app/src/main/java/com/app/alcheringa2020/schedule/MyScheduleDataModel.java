package com.app.alcheringa2020.schedule;

import android.content.Context;

import com.app.alcheringa2020.external.CommonFunctions;
import com.app.alcheringa2020.schedule.model.MyScheduleEventModel;
import com.app.alcheringa2020.schedule.model.MyScheduleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class MyScheduleDataModel {

    public static ArrayList<MyScheduleModel> myScheduleModelArrayList(Context context) {
        ArrayList<MyScheduleModel> myScheduleModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = CommonFunctions.loadAssetsJsonObj("myschedule.json", context);
            JSONArray scheduleJsonArray = jsonObject.getJSONArray("schedule");
            for (int i = 0; i < scheduleJsonArray.length(); i++) {
                JSONObject sceduleJsonObject = scheduleJsonArray.getJSONObject(i);
                int id = sceduleJsonObject.getInt("id");
                String day = sceduleJsonObject.getString("day");
                String date = sceduleJsonObject.getString("date");
                ArrayList<MyScheduleEventModel> eventModelArrayList = new ArrayList<>();
                JSONArray eventArray = sceduleJsonObject.getJSONArray("event");
                for (int j = 0; j < eventArray.length(); j++) {
                    JSONObject eventObject = eventArray.getJSONObject(j);
                    int eventId = eventObject.getInt("id");
                    String event_name = eventObject.getString("event_name");
                    String venue = eventObject.getString("venue");
                    String start_time = eventObject.getString("start_time");
                    String end_time = eventObject.getString("end_time");

                    eventModelArrayList.add(new MyScheduleEventModel(eventId, event_name, venue, start_time, end_time));

                }
                myScheduleModelArrayList.add(new MyScheduleModel(id, day, date, eventModelArrayList));

            }
            return myScheduleModelArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myScheduleModelArrayList;
    }
}
