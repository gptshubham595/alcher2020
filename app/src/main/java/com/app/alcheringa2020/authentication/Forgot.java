package com.app.alcheringa2020.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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

import maes.tech.intentanim.CustomIntent;

public class Forgot extends AppCompatActivity {
    private EditText editTextEmail, editTextUsername;
    private AppCompatButton buttonForgot;
    private ProgressDialog progressDialog;

    private LinearLayout textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);

        textViewLogin = findViewById(R.id.textViewLogin);

        buttonForgot = findViewById(R.id.buttonForgot);

        progressDialog = new ProgressDialog(this);

        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString().trim();
                final String username = editTextUsername.getText().toString().trim();

                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(username)){ editTextEmail.setError("Enter Email"); editTextUsername.setError("Enter Username");}

                if ((!TextUtils.isEmpty(email) && TextUtils.isEmpty(username)) || (TextUtils.isEmpty(email) && !TextUtils.isEmpty(username))) {
                    editTextEmail.setError(null);
                    editTextUsername.setError(null);
                    forgot();
                }
            }
        });
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forgot.this, LoginActivity.class));
                CustomIntent.customType(Forgot.this, "fadein-to-fadeout");
            }
        });
    }

    private void forgot() {
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_Forgot,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (!jsonObject.getBoolean("error")) {
                                Intent i = new Intent(Forgot.this, ChangePass.class);
                                i.putExtra("username", username);
                                i.putExtra("email", email);
                                startActivity(i);
                                CustomIntent.customType(Forgot.this, "fadein-to-fadeout");

                            } else {
                                startActivity(new Intent(Forgot.this, LoginActivity.class));
                                CustomIntent.customType(Forgot.this, "fadein-to-fadeout");
                                Toast.makeText(Forgot.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
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
                params.put("username", username);
                params.put("email", email);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }


}
