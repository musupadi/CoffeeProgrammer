package com.destinyapp.coffeeprogrammer.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran.BabActivity;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran.ModulActivity;
import com.destinyapp.coffeeprogrammer.Model.DataModel;
import com.destinyapp.coffeeprogrammer.Model.Musupadi;
import com.destinyapp.coffeeprogrammer.R;

import java.util.List;

public class ModulAdapter extends RecyclerView.Adapter<ModulAdapter.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;

    String username,nama,email,profile,alamat,level;
    Musupadi method;
    public ModulAdapter (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_modul,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModulAdapter.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        method=new Musupadi();
        holderData.nama.setText(dm.getNama_sub_modul());
        String BASE_URL = ctx.getString(R.string.BASE_URL);
        String URL = BASE_URL+"img/profile/"+dm.getCover_sub_modul();
        Glide.with(ctx)
                .load(URL)
                .into(holderData.gambar);
        holderData.dm=dm;
        holderData.modul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, BabActivity.class);
                goInput.putExtra("NAMA_MODUL",dm.getNama_sub_modul());
                goInput.putExtra("ID_SUB_MODUL",dm.getId_sub_modul());
                goInput.putExtra("ISI_SUB_MODUL",dm.getDescription_sub_modul());
                ctx.startActivity(goInput);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama;
        ImageView gambar;
        LinearLayout modul;
        DataModel dm;
        HolderData(View v){
            super(v);
            gambar=v.findViewById(R.id.ivImage);
            nama=v.findViewById(R.id.tvJudulModul);
            modul=v.findViewById(R.id.linearModul);
        }
    }
}



