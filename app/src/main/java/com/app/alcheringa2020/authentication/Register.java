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

public class Register extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextUsername, editTextEmail, editTextPassword, rTextCnfPassword;
    private AppCompatButton buttonRegister;
    private ProgressDialog progressDialog;
    boolean go = false;
    private LinearLayout textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        rTextCnfPassword = findViewById(R.id.rTextCnfPassword);
        textViewLogin = findViewById(R.id.textViewLogin);

        buttonRegister = findViewById(R.id.buttonRegister);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void registerUser() {
//        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            if (!jsonObject.getBoolean("error")) {
                                Intent i = new Intent(Register.this, Verify.class);
                                i.putExtra("username", username);
                                startActivity(i);
                                CustomIntent.customType(Register.this, "fadein-to-fadeout");
                            } else {
                                Toast.makeText(Register.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
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
                params.put("password", password);
                return params;
            }
        };

        stringRequest.setShouldCache(false);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            final String email = editTextEmail.getText().toString().trim();
            final String username = editTextUsername.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();
            final String cnfpassword = rTextCnfPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) editTextEmail.setError("Enter Email");
            if (TextUtils.isEmpty(username)) editTextUsername.setError("Enter Username");
            if (TextUtils.isEmpty(password)) editTextPassword.setError("Enter Password");
            if (TextUtils.isEmpty(cnfpassword)) rTextCnfPassword.setError("Enter Password");

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(cnfpassword)) {
                if (cnfpassword.equals(password)) {
                    editTextEmail.setError(null);
                    editTextUsername.setError(null);
                    editTextPassword.setError(null);
                    rTextCnfPassword.setError(null);
                    registerUser();
                } else {
                    rTextCnfPassword.setError("Password Not Matched!!");
                }
            }
        }
        if (view == textViewLogin)
            startActivity(new Intent(this, LoginActivity.class));
        CustomIntent.customType(this, "fadein-to-fadeout");
    }
}
