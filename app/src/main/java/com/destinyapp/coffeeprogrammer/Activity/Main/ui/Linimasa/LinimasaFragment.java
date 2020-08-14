package com.destinyapp.coffeeprogrammer.Activity.Main.ui.Linimasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.destinyapp.coffeeprogrammer.Activity.Login.LoginActivity;
import com.destinyapp.coffeeprogrammer.Model.Musupadi;
import com.destinyapp.coffeeprogrammer.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class LinimasaFragment extends Fragment {


    Musupadi musupadi = new Musupadi();
    CircleImageView ProfileImg;
    TextView nama;
    public LinimasaFragment() {
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
        return inflater.inflate(R.layout.fragment_linimasa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama=view.findViewById(R.id.tvNama);
        ProfileImg=view.findViewById(R.id.ivProfile);
//        checkLoginStatus();
    }
//    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//            if (currentAccessToken==null){
//
//            }else{
//                loaduserProfile(currentAccessToken);
//            }
//        }
//    };
//    public void checkLoginStatus(){
//        if(AccessToken.getCurrentAccessToken()!=null){
//            loaduserProfile(AccessToken.getCurrentAccessToken());
//        }else{
//            Intent intent = new Intent(getActivity(), LoginActivity.class);
//            startActivity(intent);
//            getActivity().finish();
//        }
//    }
//    public void loaduserProfile(AccessToken newAccessToken){
//        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                try {
//                    String first_name = object.getString("first_name");
//                    String last_name = object.getString("last_name");
//                    String Email = object.getString("email");
//                    String id = object.getString("id");
//                    String image_url = "https://graph.facebook.com/"+id+"/picture?type=large";
//                    nama.setText(first_name+" "+last_name);
//                    Glide.with(getActivity())
//                            .load(image_url)
//                            .into(ProfileImg);
//
//
////                    Toast.makeText(getActivity(), Email, Toast.LENGTH_SHORT).show();
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.dontAnimate();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Bundle parameters  = new Bundle();
//        parameters.putString("fields","first_name,last_name,email,id");
//        request.setParameters(parameters);
//        request.executeAsync();
//    }
}