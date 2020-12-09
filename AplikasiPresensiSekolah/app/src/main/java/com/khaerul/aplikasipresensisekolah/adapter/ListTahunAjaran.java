package com.khaerul.aplikasipresensisekolah.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.model.master.DataTahunajaranItem;
import com.khaerul.aplikasipresensisekolah.module.Variable;
import com.khaerul.aplikasipresensisekolah.views.JenjangActivity;

public class ListTahunAjaran extends RecyclerView.Adapter<ListTahunAjaran.MyViewHolder> {

    Context context;
    List<DataTahunajaranItem> listDataTahunAjaran;
    Variable newVariable = new Variable();

    public ListTahunAjaran(Context context, List<DataTahunajaranItem> listDataTahunAjaran) {
        this.context = context;
        this.listDataTahunAjaran = listDataTahunAjaran;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlist_tahunajaran, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        int identifasiTA = Integer.parseInt(listDataTahunAjaran.get(position).getIdentifikasi());



        if (identifasiTA == Variable.GET_YEAR)
        {
            Log.d("LOG", "IDF: Sedang Berlangsung" + identifasiTA);
            holder.txIdentifkasiTA.setText("TA. Sedang Berlangsung");
            holder.imvKet.setImageResource(R.drawable.ic_onair);
        } else {
            Log.d("LOG", "IDF: Sudah Lewat" + identifasiTA);
            holder.txIdentifkasiTA.setText("TA. Tidak Aktif");
            holder.imvKet.setImageResource(R.drawable.ic_check);
        }

        holder.txTahunAjaran.setText("TA. " + listDataTahunAjaran.get(position).getTahunajaran());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle packageTahunAjaran = new Bundle();
                packageTahunAjaran.putString("THN_AJARAN", listDataTahunAjaran.get(position).getTahunajaran());

                Intent kirimTA = new Intent(context, JenjangActivity.class);
                kirimTA.putExtras(packageTahunAjaran);

                context.startActivity(kirimTA);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDataTahunAjaran.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txTahunAjaran, txIdentifkasiTA;
        ImageView imvKet;

        public MyViewHolder(View itemView) {
            super(itemView);
            txTahunAjaran = (TextView)itemView.findViewById(R.id.txtTahunAjaran);
            txIdentifkasiTA = (TextView)itemView.findViewById(R.id.txtIdentifikasiTA);
            imvKet = (ImageView)itemView.findViewById(R.id.ivKetTA);
        }
    }
}
