package com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.destinyapp.coffeeprogrammer.R;

public class DetailSubModulActivity extends AppCompatActivity {
    ImageView back;
    TextView Judul,isi,JudulPelajaran;
    LinearLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sub_modul);

        Intent data = getIntent();
        String NAMA = data.getStringExtra("NAMA");
        String NO_BAB = data.getStringExtra("NO_BAB");
        String ISI = data.getStringExtra("ISI");

        back=findViewById(R.id.ivBack);
        Judul=findViewById(R.id.tvNamaModul);
        isi=findViewById(R.id.tvPelajaran);
        JudulPelajaran=findViewById(R.id.tvJudulPelajaran);
        loading=findViewById(R.id.linearLoading);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Judul.setText(NAMA);
        isi.setText(ISI);
        JudulPelajaran.setText(NO_BAB+" "+NAMA);
    }
}