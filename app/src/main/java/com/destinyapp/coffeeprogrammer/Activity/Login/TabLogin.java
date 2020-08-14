package com.destinyapp.coffeeprogrammer.Activity.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.destinyapp.coffeeprogrammer.API.ApiRequest;
import com.destinyapp.coffeeprogrammer.API.RetroServer;
import com.destinyapp.coffeeprogrammer.Activity.Main.MainActivity;
import com.destinyapp.coffeeprogrammer.Model.ResponseModel;
import com.destinyapp.coffeeprogrammer.R;
import com.destinyapp.coffeeprogrammer.SharedPreferance.DB_Helper;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.FacebookSdk.setAutoLogAppEventsEnabled;

public class TabLogin extends Fragment {
    DB_Helper dbHelper;
    EditText email,password;
    Button submit;
    LinearLayout loading;
    ScrollView mains;
    LoginButton loginButton;
    LinearLayout facebook;
    CallbackManager callbackManager;
    TextView Lupa;
    public TabLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        email=view.findViewById(R.id.etEmail);
//        loginButton=view.findViewById(R.id.loginButton);
        password=view.findViewById(R.id.etPassword);
        submit=view.findViewById(R.id.btnLogin);
        loading=view.findViewById(R.id.linearLoading);
        mains=view.findViewById(R.id.scrollMain);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mains.setAlpha((float) 0.1);
                loading.setVisibility(View.VISIBLE);
                Checker();
            }
        });


    }

    private void Checker(){
        if (email.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            normal();
        }else if(password.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            normal();
        }else{
            if (!email.getText().toString().contains("@")){
                Toast.makeText(getActivity(), "Email Harus berisi Email", Toast.LENGTH_SHORT).show();
                normal();
            }else{
                Logic();
            }
        }
    }
    private void Logic(){
        dbHelper = new DB_Helper(getActivity());
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> login = api.Login(email.getText().toString(),password.getText().toString());
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatus().equals("success")){
                        normal();
                        dbHelper.saveSession(response.body().getData().get(0).id_users,response.body().getData().get(0).nama_users);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else{
                        Toast.makeText(getActivity(), "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                        normal();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan"+e.toString(), Toast.LENGTH_SHORT).show();
                    normal();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                normal();
            }
        });
    }
    private void normal(){
        mains.setAlpha(1);
        loading.setVisibility(View.GONE);
    }
}