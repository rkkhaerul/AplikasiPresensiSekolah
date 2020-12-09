package com.khaerul.aplikasipresensisekolah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.model.filter.DatasiswaItem;

public class FilterClass extends RecyclerView.Adapter<FilterClass.MyViewHolder> {

    Context context;
    List<DatasiswaItem> datasiswaItems;
    String getTA;

    public FilterClass(Context context, List<DatasiswaItem> datasiswaItems, String getTA) {
        this.context = context;
        this.datasiswaItems = datasiswaItems;
        this.getTA = getTA;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemlist_siswafilter, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtSiswa.setText(datasiswaItems.get(position).getNamaSiswa() + "\n" + datasiswaItems.get(position).getNIS());


    }

    @Override
    public int getItemCount() {
        return datasiswaItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtSiswa;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtSiswa = (TextView)itemView.findViewById(R.id.txtNamenNIM);

        }
    }
}
