package com.app.alcheringa2020.authentication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.alcheringa2020.MainActivity;
import com.app.alcheringa2020.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.FirebaseApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import maes.tech.intentanim.CustomIntent;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername, editTextPassword;
    private AppCompatButton buttonLogin;
    private ProgressDialog progressDialog;
    private TextView btnlinktoforget;
    private LinearLayout btnLinkToRegisterScreen;
    CircleImageView fbloginbtn;
    LoginButton fblogin;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        btnLinkToRegisterScreen = findViewById(R.id.btnLinkToRegisterScreen);
        btnlinktoforget = findViewById(R.id.btnLinkToForgot);

        fbloginbtn = findViewById(R.id.fb_sign_in_button);
        fblogin = findViewById(R.id.fblogin);
        fbloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fblogin.performClick();
            }
        });

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);


        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please wait...");

        buttonLogin.setOnClickListener(this);
        btnLinkToRegisterScreen.setOnClickListener(this);
        btnlinktoforget.setOnClickListener(this);

        callbackManager= CallbackManager.Factory.create();
        fblogin.setReadPermissions(Arrays.asList("email","public_profile"));
        fblogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null){
//                txtemail.setText("");
//                profile.setImageResource(0);

                Toast.makeText(LoginActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            }else{
                loaduserProfile(currentAccessToken);
            }
        }
    };

    private  void loaduserProfile(AccessToken newAccessToken){
        GraphRequest request= GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String fname=object.getString("first_name");
                    String lname=object.getString("last_name");
                    String email=object.getString("email");
                    String id=object.getString("id");
                    String img="https://graph.facebook.com/"+id+"/picture?type=normal";

//                    Toast.makeText(LoginActivity.this, "fname", Toast.LENGTH_SHORT).show();
//                    txtemail.setText(email);
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.dontAnimate();
//                    Glide.with(LoginActivity.this).load(img).into(profile);

                    userLogin2(email,id);
                    SharedPrefManager.getInstance(LoginActivity.this).isLogin(1,fname,email);

                    Intent i=new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("img_url",img);
//                    Toast.makeText(LoginActivity.this, ""+img, Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle param=new Bundle();
        param.putString("fields","first_name,last_name,email,id");
        request.setParameters(param);
        request.executeAsync();
    }

    private void userLogin2(final String email, final String fb_id) {
        int index=email.indexOf('@');
        final String username=email.substring(0,index);
        progressDialog.setMessage("Loggin In user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGINFB,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("JSON",jsonObject+"");
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

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
                params.put("fb_id", fb_id);
                params.put("email", email);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
//        Toast.makeText(getApplicationContext(), "" + stringRequest, Toast.LENGTH_LONG).show();

    }

    private void userLogin() {

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
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

                            Log.d("JSON",jsonObject+"");
                            Toast.makeText(getApplicationContext(), jsonObject.getBoolean("error")+"", Toast.LENGTH_SHORT).show();

                            Log.d("JSON RES===",""+jsonObject);

                            if (!jsonObject.getBoolean("error")) {
                                SharedPrefManager.getInstance(LoginActivity.this).isLogin(1,username,username);
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");
                            }
                            else{
                                if(jsonObject.getString("message").equals("ERROR")){
                                    Intent i = new Intent(LoginActivity.this, Verify.class);
                                    i.putExtra("username", username);
                                    startActivity(i);
                                    CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");
                                }
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
                params.put("username", username);
                params.put("email", username);
                params.put("password", password);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
//        Toast.makeText(getApplicationContext(), "" + stringRequest, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin) {
            final String username = editTextUsername.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username)) editTextUsername.setError("Enter Username");
            if (TextUtils.isEmpty(password)) editTextPassword.setError("Enter Password");

            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                editTextUsername.setError(null);
                editTextPassword.setError(null);
                userLogin();
            }


        }
        if (view == btnLinkToRegisterScreen) {
            startActivity(new Intent(getApplicationContext(), Register.class));
            CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");
        }
        if (view == btnlinktoforget) {
            startActivity(new Intent(getApplicationContext(), Forgot.class));
            CustomIntent.customType(LoginActivity.this, "fadein-to-fadeout");
        }

    }


}
