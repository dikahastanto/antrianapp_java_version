package com.dikahastanto.antrian.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dikahastanto.antrian.R;
import com.dikahastanto.antrian.models.Antrian;

import java.util.List;

public class AntrianAdapter extends RecyclerView.Adapter<AntrianAdapter.AntrianViewHolder> {

    private Context mCtx;
    private List<Antrian> antrianList;

    public AntrianAdapter(Context mCtx, List<Antrian> antrianList) {
        this.mCtx = mCtx;
        this.antrianList = antrianList;
    }

    @NonNull
    @Override
    public AntrianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_antrian, parent,false);
        return new AntrianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AntrianViewHolder holder, int position) {

        Antrian antrian = antrianList.get(position);

        holder.txt_no_panggil.setText(antrian.getNo_panggil());
        holder.txt_username.setText(antrian.getUsername());
        holder.txt_nama_lengkap.setText(antrian.getNama_lengkap());
        holder.txt_jam.setText(antrian.getJam());
        holder.txt_status.setText(antrian.getStatus());
        holder.txt_created_at.setText(antrian.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return antrianList.size();
    }

    class AntrianViewHolder extends RecyclerView.ViewHolder{

        TextView txt_no_panggil,txt_username,txt_nama_lengkap,txt_jam,txt_status,txt_created_at;


        public AntrianViewHolder(View itemView) {
            super(itemView);

            txt_no_panggil = itemView.findViewById(R.id.txt_no_panggil);
            txt_username = itemView.findViewById(R.id.txt_username);
            txt_nama_lengkap = itemView.findViewById(R.id.txt_nama_lengkap);
            txt_jam = itemView.findViewById(R.id.txt_jam);
            txt_status = itemView.findViewById(R.id.txt_status);
            txt_created_at = itemView.findViewById(R.id.txt_created_at);
        }
    }

}
