package com.app.alcheringa2020.profile;

import android.content.Context;

import com.app.alcheringa2020.external.CommonFunctions;
import com.app.alcheringa2020.profile.model.TeamModel;
import com.app.alcheringa2020.schedule.model.EventModel;
import com.app.alcheringa2020.schedule.model.ScheduleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jiaur Rahman on 10-Jan-20.
 */
public class TeamDataModel {
    public static ArrayList<TeamModel> teamModelArrayList(Context context) {
        ArrayList<TeamModel> teamModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = CommonFunctions.loadAssetsJsonObj("team.json", context);
            JSONArray teamJsonArray = jsonObject.getJSONArray("team");
            for (int i = 0; i < teamJsonArray.length(); i++) {
                JSONObject teamJsonObject = teamJsonArray.getJSONObject(i);
                int id = teamJsonObject.getInt("id");
                String name = teamJsonObject.getString("name");
                String code = teamJsonObject.getString("code");
                String image = teamJsonObject.getString("image");
                teamModelArrayList.add(new TeamModel(id, name, code, image));

            }
            return teamModelArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return teamModelArrayList;
    }
}
