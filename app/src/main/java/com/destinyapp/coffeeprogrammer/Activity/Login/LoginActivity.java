package com.destinyapp.coffeeprogrammer.Activity.Login;
import com.bumptech.glide.request.RequestOptions;
import com.destinyapp.coffeeprogrammer.Activity.Main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.destinyapp.coffeeprogrammer.Adapter.TabPagerAdapter;
import com.destinyapp.coffeeprogrammer.R;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    TabLayout Table;
    ViewPager viewPager;
    CallbackManager callbackManager;
    LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Table=findViewById(R.id.tableLayout);
        viewPager = findViewById(R.id.viewpager);
        loginButton = findViewById(R.id.loginButton);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new TabLogin(),"Masuk");
        adapter.AddFragment(new TabRegister(),"Daftar");
        viewPager.setAdapter(adapter);
        Table.setupWithViewPager(viewPager);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("email","public_profile"));
        checkLoginStatus();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
//                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
//                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken==null){
                Toast.makeText(LoginActivity.this, "Belum Login", Toast.LENGTH_SHORT).show();
            }else{
                loaduserProfile(currentAccessToken);
            }
        }
    };

    private void loaduserProfile(AccessToken newAccessToken){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String Email = object.getString("email");
                    String id = object.getString("id");

                    String image_url = "https://graph.facebook.com"+id+"/picture?type=normal";
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
//                    Toast.makeText(getActivity(), Email, Toast.LENGTH_SHORT).show();
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters  = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }
    private void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken()!=null){
            loaduserProfile(AccessToken.getCurrentAccessToken());
        }
    }
}