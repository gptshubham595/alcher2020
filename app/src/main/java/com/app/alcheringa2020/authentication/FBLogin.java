package com.app.alcheringa2020.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.alcheringa2020.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FBLogin extends AppCompatActivity {
    LoginButton fblogin;
    ImageView profile;
    TextView txtemail;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fblogin);
    fblogin=findViewById(R.id.fb_button);
    profile=findViewById(R.id.profile);
    txtemail=findViewById(R.id.email);

    startActivity(new Intent(this,LoginActivity.class));

    callbackManager=CallbackManager.Factory.create();
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
                txtemail.setText("");
                profile.setImageResource(0);

                Toast.makeText(FBLogin.this, "Logged Out", Toast.LENGTH_SHORT).show();
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

                    txtemail.setText(email);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();
                    Glide.with(FBLogin.this).load(img).into(profile);

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
}
