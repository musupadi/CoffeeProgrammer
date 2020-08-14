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

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran.BabActivity;
import com.destinyapp.coffeeprogrammer.Activity.Main.ui.Pelajaran.SubBabActivity;
import com.destinyapp.coffeeprogrammer.Model.DataModel;
import com.destinyapp.coffeeprogrammer.Model.Musupadi;
import com.destinyapp.coffeeprogrammer.R;

import java.util.List;

public class BabAdapter extends RecyclerView.Adapter<BabAdapter.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;

    String username,nama,email,profile,alamat,level;
    Musupadi method;
    public BabAdapter (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_bab,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BabAdapter.HolderData holderData, final int posistion) {
        final DataModel dm = mList.get(posistion);
        method=new Musupadi();
        holderData.nomor.setText(String.valueOf(posistion+1));
        holderData.nama.setText(dm.getIsi_bab());
        holderData.bab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, SubBabActivity.class);
                goInput.putExtra("NAMA_BAB",dm.getIsi_bab());
                goInput.putExtra("NO_BAB",String.valueOf(posistion+1));
                goInput.putExtra("ID_BAB",dm.getId_bab());
                goInput.putExtra("VIDEO_BAB",dm.getVideo_bab());
                ctx.startActivity(goInput);
            }
        });
        holderData.dm=dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama,nomor;
        ImageView check;
        LinearLayout bab;
        DataModel dm;
        HolderData(View v){
            super(v);
            nama=v.findViewById(R.id.tvJudul);
            nomor=v.findViewById(R.id.tvNo);
            check=v.findViewById(R.id.ivCheck);
            bab=v.findViewById(R.id.linearBab);
        }
    }
}
