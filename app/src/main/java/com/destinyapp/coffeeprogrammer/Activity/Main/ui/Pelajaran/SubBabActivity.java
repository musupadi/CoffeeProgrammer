package com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.destinyapp.coffeeprogrammer.API.ApiRequest;
import com.destinyapp.coffeeprogrammer.API.RetroServer;
import com.destinyapp.coffeeprogrammer.Adapter.BabAdapter;
import com.destinyapp.coffeeprogrammer.Adapter.SubBabAdapter;
import com.destinyapp.coffeeprogrammer.Model.DataModel;
import com.destinyapp.coffeeprogrammer.Model.ResponseModel;
import com.destinyapp.coffeeprogrammer.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubBabActivity extends AppCompatActivity {
    ImageView back;
    TextView Judul;
    LinearLayout loading,tumbnail,play;
    VideoView video;
    RecyclerView recycler;
    private MediaController mediaController;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    int Playing = 0;
    boolean VideoPlay = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_bab);
        back=findViewById(R.id.ivBack);
        Judul=findViewById(R.id.tvNamaBab);
        loading=findViewById(R.id.linearLoading);
        video=findViewById(R.id.videoPlay);
        tumbnail=findViewById(R.id.linearTumbnails);
        play=findViewById(R.id.linearPlay);
        recycler=findViewById(R.id.recycler);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent data = getIntent();
        String NAMA = data.getStringExtra("NAMA_BAB");
        String NO = data.getStringExtra("NO_BAB");
        String ID = data.getStringExtra("ID_BAB");
        final String VIDEO = data.getStringExtra("VIDEO_BAB");
        Uri uri = Uri.parse(VIDEO);
        video.setMediaController(mediaController);
        video.setVideoURI(uri);
        Judul.setText(NO+"."+NAMA);
        VIDEOS();
        getData(ID,NO);
    }
    private void getData(String ID, final String No){
        loading.setVisibility(View.VISIBLE);
        recycler.setLayoutManager(new GridLayoutManager(SubBabActivity.this, 1));
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data = api.SubBab(ID);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatus().equals("success")){
                        loading.setVisibility(View.GONE);
                        mItems=response.body().getData();
                        mAdapter = new SubBabAdapter(SubBabActivity.this,mItems,No);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        loading.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    loading.setVisibility(View.VISIBLE);
                    Toast.makeText(SubBabActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                loading.setVisibility(View.VISIBLE);
            }
        });
    }
    private void VIDEOS(){
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tumbnail.setVisibility(View.GONE);
                play.setVisibility(View.GONE);
                if (Playing >= 1){
                    if (!VideoPlay){
                        video.resume();
                        VideoPlay=true;
                    }else{
                        video.pause();
                        VideoPlay=false;
                    }
                }else{
                    if (!VideoPlay){
                        video.start();
                        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {

                            }
                        });
                        Playing=Playing + 1;
                        VideoPlay=true;
                    }else{
                        video.pause();
                        VideoPlay=false;
                    }
                }
                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!VideoPlay){
                            video.resume();
                            VideoPlay=true;
                        }else{
                            video.pause();
                            play.setVisibility(View.VISIBLE);
                            VideoPlay=false;
                        }
                    }
                });
            }
        });
    }

}