package com.app.alcheringa2020.schedule;

import android.content.Context;

import com.app.alcheringa2020.external.CommonFunctions;
import com.app.alcheringa2020.events.model.ItemModel;
import com.app.alcheringa2020.events.model.JudgeModel;
import com.app.alcheringa2020.events.model.ProgrammeModel;
import com.app.alcheringa2020.events.model.RuleModel;
import com.app.alcheringa2020.schedule.model.EventModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;
import com.app.alcheringa2020.schedule.model.VanueModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class ScheduleDataModel {
    public static ArrayList<ScheduleModel> scheduleModelArrayList(Context context) {
        ArrayList<ScheduleModel> scheduleModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = CommonFunctions.loadAssetsJsonObj("schedule.json", context);
            JSONArray scheduleJsonArray = jsonObject.getJSONArray("schedule");
            for (int i = 0; i < scheduleJsonArray.length(); i++) {
                JSONObject sceduleJsonObject = scheduleJsonArray.getJSONObject(i);
                int id = sceduleJsonObject.getInt("id");
                String day = sceduleJsonObject.getString("day");
                String date = sceduleJsonObject.getString("date");
                ArrayList<VanueModel> vanueModelArrayList = new ArrayList<>();
                JSONArray vanuetArray = sceduleJsonObject.getJSONArray("venue");
                for (int j = 0; j < vanuetArray.length(); j++) {
                    JSONObject vanuetObject = vanuetArray.getJSONObject(j);
                    int vanueId = vanuetObject.getInt("id");
                    String venue_name = vanuetObject.getString("venue_name");


                    ArrayList<EventModel> eventModelArrayList = new ArrayList<>();
                    JSONArray eventArray = vanuetObject.getJSONArray("event");
                    for (int k = 0; k < eventArray.length(); k++) {
                        JSONObject eventObject = eventArray.getJSONObject(k);
                        int eventId = eventObject.getInt("id");
                        String event_name = eventObject.getString("event_name");
                        String start_time = eventObject.getString("start_time");
                        String end_time = eventObject.getString("end_time");
                        String vanue = eventObject.getString("venue_name");

                        eventModelArrayList.add(new EventModel(eventId, event_name, start_time, end_time,vanue));

                    }
                    vanueModelArrayList.add(new VanueModel(vanueId, venue_name, eventModelArrayList));
                }

                scheduleModelArrayList.add(new ScheduleModel(id, day, date, vanueModelArrayList));

            }
            return scheduleModelArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return scheduleModelArrayList;
    }
}
