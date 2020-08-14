package com.destinyapp.coffeeprogrammer.Activity.Main.ui.Akun;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.destinyapp.coffeeprogrammer.API.ApiRequest;
import com.destinyapp.coffeeprogrammer.API.RetroServer;
import com.destinyapp.coffeeprogrammer.Activity.Login.LoginActivity;
import com.destinyapp.coffeeprogrammer.Model.Musupadi;
import com.destinyapp.coffeeprogrammer.Model.ResponseModel;
import com.destinyapp.coffeeprogrammer.R;
import com.destinyapp.coffeeprogrammer.SharedPreferance.DB_Helper;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AkunFragment extends Fragment {


    Button pesan,lainnya,keluar;
    CircleImageView ProfileImg;
    TextView nama,email;
    Musupadi musupadi = new Musupadi();
    DB_Helper dbHelper;
    String ID,NAME;
    public AkunFragment() {
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
        return inflater.inflate(R.layout.fragment_akun, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama=view.findViewById(R.id.tvNama);
        email=view.findViewById(R.id.tvEmail);
        ProfileImg=view.findViewById(R.id.ivProfile);
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkSession();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                ID = cursor.getString(0);
                NAME = cursor.getString(1);
            }
        }
        if (ID != null){
            getData();
        }else{
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
//        keluar=view.findViewById(R.id.btnKeluar);
//        checkLoginStatus();
//        keluar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LoginManager.getInstance().logOut();
//            }
//        });
    }
    private void getData(){
        nama.setText(NAME);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> data = api.UsersData(ID);
        data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                email.setText(response.body().getData().get(0).email_users);
                String image_url = getActivity().getString(R.string.BASE_URL)+"img/profile/"+response.body().getData().get(0).image_users;
                Glide.with(getActivity())
                        .load(image_url)
                        .into(ProfileImg);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken==null){
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }else{
                loaduserProfile(currentAccessToken);
            }
        }
    };
    public void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken()!=null){
            loaduserProfile(AccessToken.getCurrentAccessToken());
        }else{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
    public void loaduserProfile(AccessToken newAccessToken){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String Email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+id+"/picture?type=large";
                    nama.setText(first_name+" "+last_name);
                    Glide.with(getActivity())
                            .load(image_url)
                            .into(ProfileImg);
                    email.setText(Email);


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
}