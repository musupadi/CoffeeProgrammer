package com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.destinyapp.coffeeprogrammer.API.ApiRequest;
import com.destinyapp.coffeeprogrammer.API.RetroServer;
import com.destinyapp.coffeeprogrammer.Adapter.BabAdapter;
import com.destinyapp.coffeeprogrammer.Adapter.ModulAdapter;
import com.destinyapp.coffeeprogrammer.Model.DataModel;
import com.destinyapp.coffeeprogrammer.Model.ResponseModel;
import com.destinyapp.coffeeprogrammer.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabActivity extends AppCompatActivity {
    ImageView back;
    TextView Judul,isi;
    LinearLayout loading;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bab);

        Judul = findViewById(R.id.tvNamaBab);
        isi = findViewById(R.id.tvIsiBab);
        back = findViewById(R.id.ivBack);
        loading = findViewById(R.id.linearLoading);
        recyclerView = findViewById(R.id.recycler);
        Intent data = getIntent();
        String NAMA = data.getStringExtra("NAMA_MODUL");
        String ID = data.getStringExtra("ID_SUB_MODUL");
        String ISI = data.getStringExtra("ISI_SUB_MODUL");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Judul.setText(NAMA);
        isi.setText(ISI);
        getData(ID);
    }
    private void getData(String ID){
        loading.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(BabActivity.this, 1));
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data = api.Bab(ID);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("success")){
                    loading.setVisibility(View.GONE);
                    mItems=response.body().getData();
                    mAdapter = new BabAdapter(BabActivity.this,mItems);
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(BabActivity.this, "Tydac Ada", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                    loading.setVisibility(View.VISIBLE);
            }
        });
    }
}