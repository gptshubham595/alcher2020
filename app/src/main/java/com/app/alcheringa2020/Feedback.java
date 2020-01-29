package com.app.alcheringa2020;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.alcheringa2020.authentication.Constants;
import com.app.alcheringa2020.authentication.RequestHandler;
import com.app.alcheringa2020.authentication.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Feedback extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AppCompatButton btnfeed;
    private ProgressDialog progressDialog;
    EditText email, subject, text;
    AppCompatSpinner tagspinner;
    String item="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btnfeed = findViewById(R.id.buttonFeedback);
        email = findViewById(R.id.editemail);
        subject = findViewById(R.id.subject);
        text = findViewById(R.id.text);
        progressDialog = new ProgressDialog(this);
        final String emailstr = SharedPrefManager.getInstance(this).getUserEmail();
        email.setText(emailstr);

        final String substr = subject.getText().toString();
        final String textstr = text.getText().toString();

        btnfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendfeed(emailstr, substr, textstr,item);
            }
        });

        tagspinner = findViewById(R.id.tag);
        tagspinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("General");
        categories.add("Technical");
        categories.add("Competition");
        categories.add("Festival");
        categories.add("Payment");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        tagspinner.setAdapter(dataAdapter);

    }

    private void sendfeed(final String email, final String sub, final String textstr, final String tag) {
        final String substr = subject.getText().toString();
        final String text1 = text.getText().toString();

//        Toast.makeText(this, email+" "+substr+" "+text1 + " "+tag, Toast.LENGTH_SHORT).show();
        progressDialog.setMessage("Sending...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_Feed,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            if (!jsonObject.getBoolean("error")) {
                                Toast.makeText(Feedback.this, "Feedback Sent", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Feedback.this, MainActivity.class);
                                startActivity(i);
                                CustomIntent.customType(Feedback.this, "fadein-to-fadeout");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("subject", substr);
                params.put("text", text1);
                params.put("tag", tag);
                params.put("submit", "submit");
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

        String substr = subject.getText().toString();
        String textstr = text.getText().toString();


        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
