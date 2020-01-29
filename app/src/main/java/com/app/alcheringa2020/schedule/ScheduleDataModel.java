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
import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Jiaur Rahman on 07-Jan-20.
 */
public class ScheduleDataModel {
    public static ArrayList<ScheduleModel> scheduleModelArrayList(Context context) {
        JSONObject jsonObject;
        String schedule="{\n" +
                "  \"schedule\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"day\": \"Day 0\",\n" +
                "      \"date\": \"06/01/20\",\n" +
                "      \"venue\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"venue_name\": \"Hall 1\",\n" +
                "          \"event\": [\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"Rock-O-Phonix\",\n" +
                "              \"start_time\": \"14:00\",\n" +
                "              \"end_time\": \"15:00\",\n" +
                "              \"venue_name\": \"Hall 1\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event1\",\n" +
                "              \"start_time\": \"16:00\",\n" +
                "              \"end_time\": \"17:00\",\n" +
                "              \"venue_name\": \"Hall 1\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"venue_name\": \"Hall 2\",\n" +
                "          \"event\": [\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event0\",\n" +
                "              \"start_time\": \"14:00\",\n" +
                "              \"end_time\": \"15:00\",\n" +
                "              \"venue_name\": \"Hall 2\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event1\",\n" +
                "              \"start_time\": \"16:00\",\n" +
                "              \"end_time\": \"17:00\",\n" +
                "              \"venue_name\": \"Hall 2\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"day\": \"Day 1\",\n" +
                "      \"date\": \"07/01/20\",\n" +
                "      \"venue\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"venue_name\": \"Hall 3\",\n" +
                "          \"event\": [\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"Rock-O-Phonix\",\n" +
                "              \"start_time\": \"14:00\",\n" +
                "              \"end_time\": \"15:00\",\n" +
                "              \"venue_name\": \"Hall 1\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event1\",\n" +
                "              \"start_time\": \"16:00\",\n" +
                "              \"end_time\": \"17:00\",\n" +
                "              \"venue_name\": \"Hall 1\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"venue_name\": \"Hall 4\",\n" +
                "          \"event\": [\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event0\",\n" +
                "              \"start_time\": \"14:00\",\n" +
                "              \"end_time\": \"15:00\",\n" +
                "              \"venue_name\": \"Hall 2\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event1\",\n" +
                "              \"start_time\": \"16:00\",\n" +
                "              \"end_time\": \"17:00\",\n" +
                "              \"venue_name\": \"Hall 2\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"day\": \"Day 2\",\n" +
                "      \"date\": \"07/01/20\",\n" +
                "      \"venue\": [\n" +
                "        {\n" +
                "          \"id\": 1,\n" +
                "          \"venue_name\": \"Hall 3\",\n" +
                "          \"event\": [\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"Rock-O-Phonix\",\n" +
                "              \"start_time\": \"14:00\",\n" +
                "              \"end_time\": \"15:00\",\n" +
                "              \"venue_name\": \"Hall 1\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event1\",\n" +
                "              \"start_time\": \"16:00\",\n" +
                "              \"end_time\": \"17:00\",\n" +
                "              \"venue_name\": \"Hall 1\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": 2,\n" +
                "          \"venue_name\": \"Hall 4\",\n" +
                "          \"event\": [\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event0\",\n" +
                "              \"start_time\": \"14:00\",\n" +
                "              \"end_time\": \"15:00\",\n" +
                "              \"venue_name\": \"Hall 2\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": 1,\n" +
                "              \"event_name\": \"event1\",\n" +
                "              \"start_time\": \"16:00\",\n" +
                "              \"end_time\": \"17:00\",\n" +
                "              \"venue_name\": \"Hall 2\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ArrayList<ScheduleModel> scheduleModelArrayList = new ArrayList<>();
        try {
//            JSONObject jsonObject = CommonFunctions.loadAssetsJsonObj("schedule.json", context);
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
    private  static String getschedule() {
        final String[] res = {""};
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_SCHEDNEW,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            res[0] =response;
//                            jsonObject[0] = new JSONObject(response);
//                            Log.d("DUPJSON3",jsonObject[0]+"");
//                            Toast.makeText(getApplicationContext(), ""+jsonObject[0], Toast.LENGTH_SHORT).show();

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
