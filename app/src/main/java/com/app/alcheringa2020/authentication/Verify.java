package com.app.alcheringa2020.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.alcheringa2020.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Verify extends AppCompatActivity {
AppCompatButton buttonlogin;
TextView sendagain;
EditText editTextOtp;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        editTextOtp=findViewById(R.id.editTextOtp);
        sendagain=findViewById(R.id.sendOtpAgain);
        buttonlogin=findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");


        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String otp = editTextOtp.getText().toString().trim();
                if(TextUtils.isEmpty(otp) || otp.length()<6){editTextOtp.setError("Enter Correct OTP");}
                else{
                    verifyuser();
                }
            }
        });

        sendagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyagain();
            }
        });

    }
    private void verifyuser() {
        final String username = getIntent().getStringExtra("username");

        final String otp = editTextOtp.getText().toString().trim();

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_Verify,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            if(!jsonObject.getBoolean("error")) {
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userVerified(
                                                jsonObject.getString("verified")
                                        );

                                Intent i = new Intent(Verify.this, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                            }else{
                                Toast.makeText(Verify.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tag", "verify");
                params.put("otp", otp);
                params.put("username", username);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }

    private void verifyagain() {
        final String username = getIntent().getStringExtra("username");

        final String otp = editTextOtp.getText().toString().trim();

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_Verify,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            if(!jsonObject.getBoolean("error")) {
                                Toast.makeText(Verify.this, "Sent", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Verify.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tag", "send");
                params.put("username", username);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }

}
