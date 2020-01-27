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

public class ChangePass extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private EditText editTextOtp, editTextPassword, rTextCnfPassword;
    private AppCompatButton buttonChange;
    private LinearLayout textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        progressDialog = new ProgressDialog(this);

        editTextOtp = findViewById(R.id.editTextOtp);
        editTextPassword = findViewById(R.id.editTextPassword);
        rTextCnfPassword = findViewById(R.id.rTextCnfPassword);
        textViewLogin = findViewById(R.id.textViewLogin);
        buttonChange = findViewById(R.id.buttonChange);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChangePass.this, LoginActivity.class);
                startActivity(i);
                CustomIntent.customType(ChangePass.this, "fadein-to-fadeout");
            }
        });

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = editTextOtp.getText().toString().trim();
                String password = editTextOtp.getText().toString().trim();
                String cnfpassword = editTextOtp.getText().toString().trim();
                if (TextUtils.isEmpty(otp))
                    editTextOtp.setError("Enter OTP SENT IN YOUR MAIL JUST NOW");
                if (TextUtils.isEmpty(password)) editTextPassword.setError("Enter Password");
                if (TextUtils.isEmpty(cnfpassword))
                    rTextCnfPassword.setError("Enter Confirm Password");
                if (!password.equals(cnfpassword)) {
                    rTextCnfPassword.setError("Password Not matched");
                }

                if (!TextUtils.isEmpty(otp) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(cnfpassword))
                    if (password.equals(cnfpassword))
                        change(otp, password);
            }
        });
    }

    private void change(final String otp, final String password) {

        progressDialog.setMessage("Loggin In user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_Change,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), "" + jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if (!jsonObject.getBoolean("error")) {
                                Intent i = new Intent(ChangePass.this, LoginActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(ChangePass.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
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
                String username="";
                String email="";

                try {
                    username = getIntent().getStringExtra("username");
                    email = getIntent().getStringExtra("email");
                }catch(Exception e ){
                    e.printStackTrace();
                }

                params.put("username", username);
                params.put("email", email);
                params.put("otp", otp);
                params.put("password", password);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
