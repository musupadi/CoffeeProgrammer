package com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.coffeeprogrammer.API.ApiRequest;
import com.destinyapp.coffeeprogrammer.API.RetroServer;
import com.destinyapp.coffeeprogrammer.Adapter.ModulAdapter;
import com.destinyapp.coffeeprogrammer.Model.DataModel;
import com.destinyapp.coffeeprogrammer.Model.ResponseModel;
import com.destinyapp.coffeeprogrammer.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModulActivity extends AppCompatActivity {
    LinearLayout loading;
    RecyclerView recyclerView;
    ImageView back;
    TextView Judul;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul);
        loading=findViewById(R.id.linearLoading);
        recyclerView=findViewById(R.id.recycler);
        back=findViewById(R.id.ivBack);
        Judul=findViewById(R.id.tvNamaModul);
        Intent data = getIntent();
        String JUDUL = data.getStringExtra("NAMA_PELAJARAN");
        String ID_MODUL = data.getStringExtra("ID_MODUL");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Judul.setText(JUDUL);
        GetData(ID_MODUL);
    }
    private void GetData(String ID){
        loading.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(ModulActivity.this, 2));
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> data = api.SubModel(ID);
        data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatus().equals("success")){
                        loading.setVisibility(View.GONE);
                        mItems=response.body().getData();
                        mAdapter = new ModulAdapter(ModulActivity.this,mItems);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(ModulActivity.this, "Tydac Ada", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.VISIBLE);
                    }

                }catch (Exception e){
                    Toast.makeText(ModulActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
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