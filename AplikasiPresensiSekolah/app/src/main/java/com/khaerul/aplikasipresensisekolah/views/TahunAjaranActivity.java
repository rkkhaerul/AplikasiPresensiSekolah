package com.khaerul.aplikasipresensisekolah.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.adapter.ListTahunAjaran;
import com.khaerul.aplikasipresensisekolah.model.master.DataTahunajaranItem;
import com.khaerul.aplikasipresensisekolah.model.master.ResponseTahunAjaran;
import com.khaerul.aplikasipresensisekolah.network.ApiServices;
import com.khaerul.aplikasipresensisekolah.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TahunAjaranActivity extends AppCompatActivity {

    RecyclerView rvListTahunAjaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahun_ajaran);
        Toolbar toolbarTA = (Toolbar) findViewById(R.id.toolbarTA);
        setSupportActionBar(toolbarTA);
        getSupportActionBar().setTitle("Set Academic Year");
        getSupportActionBar().setSubtitle("Rabbaanii Islamic School");

        toolbarTA.setNavigationIcon(getResources().getDrawable(R.drawable.ic_tp));

        rvListTahunAjaran = (RecyclerView)findViewById(R.id.rvListTahunAjaran);
        rvListTahunAjaran.setHasFixedSize(true);
        rvListTahunAjaran.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        showListTahunAjaran();

    }

    private void showListTahunAjaran() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseTahunAjaran> dataTahunAjaran = apiServices.getTahunAjaran();

        dataTahunAjaran.enqueue(new Callback<ResponseTahunAjaran>() {
            @Override
            public void onResponse(Call<ResponseTahunAjaran> call, Response<ResponseTahunAjaran> response) {
                if (response.isSuccessful()) {
                    List<DataTahunajaranItem> tahunajaranItems = response.body().getDataTahunajaran();
                    boolean status = response.body().isStatus();
                    if (status) {
                        ListTahunAjaran adapterTahunAjaran = new ListTahunAjaran(com.khaerul.aplikasipresensisekolah.views.TahunAjaranActivity.this, tahunajaranItems);
                        rvListTahunAjaran.setAdapter(adapterTahunAjaran);
                    } else {
                        Toast.makeText(com.khaerul.aplikasipresensisekolah.views.TahunAjaranActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTahunAjaran> call, Throwable t) {

            }
        });
    }

    private void updateListTA() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseTahunAjaran> dataTahunAjaran = apiServices.getTahunAjaran();

        dataTahunAjaran.enqueue(new Callback<ResponseTahunAjaran>() {
            @Override
            public void onResponse(Call<ResponseTahunAjaran> call, Response<ResponseTahunAjaran> response) {
                if (response.isSuccessful()) {
                    List<DataTahunajaranItem> tahunajaranItems = response.body().getDataTahunajaran();
                    boolean status = response.body().isStatus();
                    if (status) {
                        ListTahunAjaran adapterTahunAjaran = new ListTahunAjaran(com.khaerul.aplikasipresensisekolah.views.TahunAjaranActivity.this, tahunajaranItems);
                        rvListTahunAjaran.setAdapter(adapterTahunAjaran);
                        Toast.makeText(com.khaerul.aplikasipresensisekolah.views.TahunAjaranActivity.this, "Data Tahun Pelajaran Berhasil di Refresh", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(com.khaerul.aplikasipresensisekolah.views.TahunAjaranActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTahunAjaran> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_itemta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemRefresh) {
            updateListTA();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}