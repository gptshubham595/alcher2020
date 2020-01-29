package com.app.alcheringa2020.schedule;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.alcheringa2020.authentication.Constants;
import com.app.alcheringa2020.authentication.RequestHandler;
import com.app.alcheringa2020.external.CommonFunctions;
import com.app.alcheringa2020.schedule.model.MyScheduleEventModel;
import com.app.alcheringa2020.schedule.model.MyScheduleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Jiaur Rahman on 11-Jan-20.
 */
public class MyScheduleDataModel {

    public static ArrayList<MyScheduleModel> myScheduleModelArrayList(Context context) {
        JSONObject jsonObject;
        String schedule="{\n" +
                "  \"schedule\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"day\": \"Day 1\",\n" +
                "      \"date\": \"07/01/20\",\n" +
                "      \"event\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"event_name\": \"Haute Couture\",\n" +
                "          \"venue\": \"Hall 1\",\n" +
                "          \"start_time\": \"13:00\",\n" +
                "          \"end_time\": \"14:30\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"event_name\": \"states of dress\",\n" +
                "          \"venue\": \"Hall 2\",\n" +
                "          \"start_time\": \"17:00\",\n" +
                "          \"end_time\": \"18:30\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"day\": \"Day 2\",\n" +
                "      \"date\": \"08/01/20\",\n" +
                "      \"event\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"event_name\": \"Rock-O-Phonix\",\n" +
                "          \"venue\": \"Hall 2\",\n" +
                "          \"start_time\": \"14:00\",\n" +
                "          \"end_time\": \"14:30\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"event_name\": \"Mr & Ms Alcheringa\",\n" +
                "          \"venue\": \"Hall 1\",\n" +
                "          \"start_time\": \"18:00\",\n" +
                "          \"end_time\": \"19:30\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"day\": \"Day 3\",\n" +
                "      \"date\": \"09/01/20\",\n" +
                "      \"event\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"event_name\": \"Rock-O-Phonix\",\n" +
                "          \"venue\": \"Hall 2\",\n" +
                "          \"start_time\": \"14:00\",\n" +
                "          \"end_time\": \"14:30\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"event_name\": \"Mr & Ms Alcheringa\",\n" +
                "          \"venue\": \"Hall 1\",\n" +
                "          \"start_time\": \"18:00\",\n" +
                "          \"end_time\": \"19:30\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ArrayList<MyScheduleModel> myScheduleModelArrayList = new ArrayList<>();
        try {
            jsonObject = CommonFunctions.loadAssetsJsonObj("myschedule.json", context);
            try{
                jsonObject=new JSONObject(getschedule());
            }
            catch(Exception e){e.printStackTrace();
                jsonObject = new JSONObject(schedule);
            }
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

    private  static String getschedule() {
        final String[] res = {""};
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_MYSCHEDNEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            res[0] =response;
//                            jsonObject[0] = new JSONObject(response);
//                            Log.d("DUPJSON3",jsonObject[0]+"");
//                            Toast.makeText(getApplicationContext(), ""+res[0], Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "REFRESHED", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "REFRESHED", Toast.LENGTH_LONG).show();

//                            json=jsonObject[0];
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        return res[0];
    }
}
