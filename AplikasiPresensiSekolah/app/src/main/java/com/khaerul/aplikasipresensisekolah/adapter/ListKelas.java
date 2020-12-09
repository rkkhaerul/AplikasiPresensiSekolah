
package com.khaerul.aplikasipresensisekolah.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.model.master.DataKelasItem;
import com.khaerul.aplikasipresensisekolah.views.ProsesAbsensiActivity;

public class ListKelas extends RecyclerView.Adapter<ListKelas.MyViewHolder> {

    Context context;
    List<DataKelasItem> listDataKelas;
    String getTA, getJenjang, getJurusan, getSubJurusan;

    public ListKelas(Context context, List<DataKelasItem> listDataKelas, String getTA, String getJenjang) {
        this.context = context;
        this.listDataKelas = listDataKelas;
        this.getTA = getTA;
        this.getJenjang = getJenjang;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlist_kelas, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String setKelas = listDataKelas.get(position).getKodeKelas();

        if (setKelas.equals("X")) {
            holder.txtShow.setText("10");
            holder.txtKelas.setText("Kelas 10/" + listDataKelas.get(position).getKodeKelas() + " (" + listDataKelas.get(position).getKelas() + ")");
            holder.txtDeskripsiKelas.setText(listDataKelas.get(position).getDeskripsi());
        } else if(setKelas.equals("XI")) {
            holder.txtShow.setText("11");
            holder.txtKelas.setText("Kelas 11/" + listDataKelas.get(position).getKodeKelas() + " (" + listDataKelas.get(position).getKelas() + ")");
            holder.txtDeskripsiKelas.setText(listDataKelas.get(position).getDeskripsi());
        } else if (setKelas.equals("XII")) {
            holder.txtShow.setText("12");
            holder.txtKelas.setText("Kelas 12/" + listDataKelas.get(position).getKodeKelas() + " (" + listDataKelas.get(position).getKelas() + ")");
            holder.txtDeskripsiKelas.setText(listDataKelas.get(position).getDeskripsi());
        } else {

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Pilih Jurusan");

                String[] itemJurusan = {"RPL", "TKJ", "All Student"};
                String[] itemSubJurusan = {"Rekayasa Perangkat Lunak", "Teknik Komputer & Jaringan", "All Student"};

                int chechedItem = 0;

                alertDialog.setSingleChoiceItems(itemSubJurusan, chechedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch (which) {
                            case 0 :
                                getJurusan = itemJurusan[which];
                                getSubJurusan = itemSubJurusan[which];
                                Toast.makeText(context, getJurusan + " (" + getSubJurusan + ")", Toast.LENGTH_SHORT).show();

                                kirimData(getTA, getJenjang, listDataKelas.get(position).getKodeKelas(), getJurusan);

                                dialogInterface.dismiss();
                                // ((KelasActivity)context).finish();
                                break;
                            case 1 :
                                getJurusan = itemJurusan[which];
                                getSubJurusan = itemSubJurusan[which];
                                Toast.makeText(context, getJurusan + " (" + getSubJurusan + ")", Toast.LENGTH_SHORT).show();

                                kirimData(getTA, getJenjang, listDataKelas.get(position).getKodeKelas(), getJurusan);

                                dialogInterface.dismiss();
                                // ((KelasActivity)context).finish();
                                break;
                            case 2:
                                getJurusan = "ALLDATA";
                                Toast.makeText(context, "Tampilkan Semua Siswa", Toast.LENGTH_SHORT).show();
                                kirimData(getTA, getJenjang, listDataKelas.get(position).getKodeKelas(), getJurusan);
                                dialogInterface.dismiss();
                                // ((KelasActivity)context).finish();
                                break;
                        }
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDataKelas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtKelas, txtDeskripsiKelas, txtShow;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtKelas = (TextView)itemView.findViewById(R.id.txKelasGet);
            txtDeskripsiKelas = (TextView)itemView.findViewById(R.id.txDeskripsiKelasGet);
            txtShow = (TextView)itemView.findViewById(R.id.txtKelasShow);
        }
    }

    public void kirimData(String TA, String Jenjang, String Kelas, String Jurusan) {
        Bundle paketData = new Bundle();
        paketData.putString("TA", TA);
        paketData.putString("JJG", Jenjang);
        paketData.putString("CLS", Kelas);
        paketData.putString("JRSN", Jurusan);

        Intent kirimData = new Intent(context, ProsesAbsensiActivity.class);
        kirimData.putExtras(paketData);
        context.startActivity(kirimData);

    }
}
