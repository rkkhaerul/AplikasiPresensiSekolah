package com.khaerul.aplikasipresensisekolah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.model.inrombel.SiswainrombelItem;

public class InRombel extends RecyclerView.Adapter<InRombel.MyViewHolder> {

    Context context;
    List<SiswainrombelItem> siswainrombelItems;
    String getTA;

    public InRombel(Context context, List<SiswainrombelItem> siswainrombelItems, String getTA) {
        this.context = context;
        this.siswainrombelItems = siswainrombelItems;
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
        holder.txtSiswa.setText(siswainrombelItems.get(position).getNamaSiswa());
        holder.txtNim.setText(siswainrombelItems.get(position).getNIS());
    }

    @Override
    public int getItemCount() {
        return siswainrombelItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtSiswa;
        TextView txtNim;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtSiswa = (TextView)itemView.findViewById(R.id.txtNamenNIM);
            txtNim = (TextView)itemView.findViewById(R.id.txtNIM);
        }
    }
}
