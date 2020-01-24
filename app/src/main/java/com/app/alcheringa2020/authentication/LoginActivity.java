package com.app.alcheringa2020.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.alcheringa2020.MainActivity;
import com.app.alcheringa2020.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextPassword;
    private AppCompatButton buttonLogin;
    private ProgressDialog progressDialog;
    private TextView btnLinkToRegisterScreen,btnlinktoforget,btnLinkToevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
        btnLinkToRegisterScreen=findViewById(R.id.btnLinkToRegisterScreen);
        btnlinktoforget=findViewById(R.id.btnLinkToForgot);
        btnLinkToevent=findViewById(R.id.btnLinkToevent);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin =  findViewById(R.id.buttonLogin);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please wait...");

        buttonLogin.setOnClickListener(this);
        btnLinkToRegisterScreen.setOnClickListener(this);
        btnlinktoforget.setOnClickListener(this);
        btnLinkToevent.setOnClickListener(this);

    }

    private void userLogin() {

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        progressDialog.setMessage("Loggin In user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            if(!jsonObject.getBoolean("error")) {
                                Intent i=new Intent(LoginActivity.this,Verify.class);
                                i.putExtra("username",username);
                                startActivity(i);
                            }else{
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
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
                params.put("password", password);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        Toast.makeText(getApplicationContext(), ""+stringRequest, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin) {
            final String username = editTextUsername.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();

            if(TextUtils.isEmpty(username)) editTextUsername.setError("Enter Username");
            if(TextUtils.isEmpty(password)) editTextPassword.setError("Enter Password");

            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                editTextUsername.setError(null);
                editTextPassword.setError(null);
                userLogin();
            }


        }
        if(view==btnLinkToRegisterScreen){
            startActivity(new Intent(getApplicationContext(), Register.class));
        }
        if(view==btnlinktoforget){
            startActivity(new Intent(getApplicationContext(), Forgot.class));
        }
        if(view== btnLinkToevent ){
            getevents();
        }
    }

    private void getevents() {

        progressDialog.setMessage("Loggin In user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_Events,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), ""+ jsonObject, Toast.LENGTH_LONG).show();

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
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
