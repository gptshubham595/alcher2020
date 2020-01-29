package com.app.alcheringa2020.events;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.alcheringa2020.authentication.Constants;
import com.app.alcheringa2020.authentication.RequestHandler;
import com.app.alcheringa2020.events.model.ItemModel;
import com.app.alcheringa2020.events.model.JudgeModel;
import com.app.alcheringa2020.events.model.ProgrammeModel;
import com.app.alcheringa2020.events.model.RuleModel;
import com.app.alcheringa2020.external.CommonFunctions;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class EventsDataModel {
    public static ArrayList<ProgrammeModel> programmeModelArrayList(Context context) {
        ArrayList<ProgrammeModel> programmeModelArrayList = new ArrayList<>();
        String event="";

        try {
            JSONObject jsonObject;
            try{
                jsonObject=new JSONObject(getevent());
                Toast.makeText(getApplicationContext(), "REFRESHED", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "REFRESHED", Toast.LENGTH_LONG).show();
//                Toast.makeText(context, ""+jsonObject, Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){e.printStackTrace();
                jsonObject = CommonFunctions.loadAssetsJsonObj("item.json",context);
            }
            Log.d("JSON2===",""+jsonObject);
            JSONArray proJsonArray = jsonObject.getJSONArray("programme");

            for (int i = 0; i < proJsonArray.length(); i++) {
                JSONObject proJsonObject = proJsonArray.getJSONObject(i);
                int proId = proJsonObject.getInt("id");
                String proCategory = proJsonObject.getString("category");
                String proImage = proJsonObject.getString("image");
                ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();
                JSONArray itemArray = proJsonObject.getJSONArray("item");
                for (int j = 0; j < itemArray.length(); j++) {
                    JSONObject itemObject = itemArray.getJSONObject(j);
                    int itemId = itemObject.getInt("id");
                    String item = itemObject.getString("item");
                    String itemImage = itemObject.getString("image");
                    String itemCompetition = itemObject.getString("competition");
                    String itemBounty = itemObject.getString("bounty");
                    String itemDescription = itemObject.getString("description");
//                    Toast.makeText(context, itemDescription, Toast.LENGTH_SHORT).show();
                    JSONArray ruleArray = itemObject.getJSONArray("rules");
                    ArrayList<RuleModel> ruleModelArrayList = new ArrayList<>();
                    for (int k = 0; k < ruleArray.length(); k++) {
                        JSONObject ruleObject = ruleArray.getJSONObject(k);
                        int ruleId = ruleObject.getInt("id");
                        String rule = ruleObject.getString("rule");
                        ruleModelArrayList.add(new RuleModel(ruleId, rule));
                    }

                    String pre_header = itemObject.getJSONObject("prelims_theme").getString("header");
                    String pre_theme = itemObject.getJSONObject("prelims_theme").getString("theme");
                    String final_header = itemObject.getJSONObject("final_theme").getString("header");
                    String final_theme = itemObject.getJSONObject("final_theme").getString("theme");
                    ArrayList<JudgeModel> judgeModelArrayList = new ArrayList<>();
                    JSONArray judgeArray = itemObject.getJSONArray("judge_criteria");
                    for (int l = 0; l < judgeArray.length(); l++) {
                        JSONObject judgeObject = judgeArray.getJSONObject(l);
                        int judgeId = judgeObject.getInt("id");
                        String judgeCriteria = judgeObject.getString("criteria");

                        judgeModelArrayList.add(new JudgeModel(judgeId, judgeCriteria));
                    }
                    itemModelArrayList.add(new ItemModel(itemId, item, itemImage, itemCompetition, itemBounty, itemDescription,
                            pre_header, pre_theme, final_header, final_theme, ruleModelArrayList, judgeModelArrayList));

                }
                programmeModelArrayList.add(new ProgrammeModel(proId, proCategory, proImage, itemModelArrayList));

            }
            return programmeModelArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return programmeModelArrayList;
    }

    private  static String getevent() {
        final String[] res = {""};
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_EventsNEW,
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
