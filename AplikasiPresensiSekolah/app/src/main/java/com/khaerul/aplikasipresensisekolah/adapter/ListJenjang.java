package com.khaerul.aplikasipresensisekolah.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.model.master.DataJenjangItem;
import com.khaerul.aplikasipresensisekolah.views.KelasActivity;

public class ListJenjang extends RecyclerView.Adapter<ListJenjang.MyViewHolder> {

    Context context;
    List<DataJenjangItem> listDataJenjang;
    String getTA;

    public ListJenjang(Context context, List<DataJenjangItem> listDataJenjang, String getTA) {
        this.context = context;
        this.listDataJenjang = listDataJenjang;
        this.getTA = getTA;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlist_jenjang, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txJenjang.setText(listDataJenjang.get(position).getJenjangPendidikan() + " " + listDataJenjang.get(position).getDetail());
        holder.txDeskripsiJenjang.setText(listDataJenjang.get(position).getInstansi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle packageTAnJenjang = new Bundle();
                packageTAnJenjang.putString("KODE_JENJANG", listDataJenjang.get(position).getJenjangPendidikan());
                packageTAnJenjang.putString("KODE_TA", getTA);
                Intent kirimTA = new Intent(context, KelasActivity.class);
                kirimTA.putExtras(packageTAnJenjang);
                context.startActivity(kirimTA);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDataJenjang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txJenjang, txDeskripsiJenjang;

        public MyViewHolder(View itemView) {
            super(itemView);

            txJenjang = (TextView)itemView.findViewById(R.id.txtJenjang);
            txDeskripsiJenjang = (TextView)itemView.findViewById(R.id.txtDetailJenjang);
        }
    }
}
