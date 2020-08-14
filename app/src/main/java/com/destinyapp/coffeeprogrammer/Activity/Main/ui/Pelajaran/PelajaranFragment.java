package com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.destinyapp.coffeeprogrammer.R;

public class PelajaranFragment extends Fragment {
    LinearLayout web,android,dekstop,ios;
    public PelajaranFragment() {
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
        return inflater.inflate(R.layout.fragment_pelajaran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        web=view.findViewById(R.id.linearWeb);
        android=view.findViewById(R.id.linearAndroid);
        dekstop=view.findViewById(R.id.linearDesktop);
        ios=view.findViewById(R.id.linearIos);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(getActivity(), ModulActivity.class);
                goInput.putExtra("NAMA_PELAJARAN","Web Developer");
                goInput.putExtra("ID_MODUL","1");
                startActivity(goInput);
            }
        });
        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(getActivity(), ModulActivity.class);
                goInput.putExtra("NAMA_PELAJARAN","Android Developer");
                goInput.putExtra("ID_MODUL","2");
                startActivity(goInput);
            }
        });
        ios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(getActivity(), ModulActivity.class);
                goInput.putExtra("NAMA_PELAJARAN","IOS Developer");
                goInput.putExtra("ID_MODUL","4");
                startActivity(goInput);
            }
        });
        dekstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(getActivity(), ModulActivity.class);
                goInput.putExtra("NAMA_PELAJARAN","Desktop Developer");
                goInput.putExtra("ID_MODUL","3");
                startActivity(goInput);
            }
        });
    }
}