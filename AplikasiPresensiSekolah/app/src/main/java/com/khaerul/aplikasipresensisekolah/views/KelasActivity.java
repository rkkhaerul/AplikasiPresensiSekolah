package com.khaerul.aplikasipresensisekolah.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.adapter.ListKelas;
import com.khaerul.aplikasipresensisekolah.model.master.DataKelasItem;
import com.khaerul.aplikasipresensisekolah.model.master.ResponseKelas;
import com.khaerul.aplikasipresensisekolah.network.ApiServices;
import com.khaerul.aplikasipresensisekolah.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelasActivity extends AppCompatActivity {

    RecyclerView rvListKelas;
    TextView txtHeader;

    String getTahunAjaran, getJenjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        Toolbar toolbarCLS = (Toolbar) findViewById(R.id.toolbarKelas);
        setSupportActionBar(toolbarCLS);
        getSupportActionBar().setTitle("Set Class");

        toolbarCLS.setNavigationIcon(getResources().getDrawable(R.drawable.ic_tp));

        if (getIntent().getExtras() != null) {
            Bundle getPackage = getIntent().getExtras();
            getTahunAjaran = getPackage.getString("KODE_TA");
            getJenjang = getPackage.getString("KODE_JENJANG");

            getSupportActionBar().setSubtitle("Level. " + getJenjang + " TA. " + getTahunAjaran);
        }

        rvListKelas = (RecyclerView)findViewById(R.id.rvListKelas);
        rvListKelas.setHasFixedSize(true);
        rvListKelas.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        showDataKelas();
    }

    private void showDataKelas() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseKelas> dataKelasLoad = apiServices.getKelas();

        dataKelasLoad.enqueue(new Callback<ResponseKelas>() {
            @Override
            public void onResponse(Call<ResponseKelas> call, Response<ResponseKelas> response) {
                if (response.isSuccessful()) {
                    List<DataKelasItem> dataKelasItemList = response.body().getDataKelas();

                    switch (getJenjang) {
                        case "SMK" :
                            boolean status = response.body().isStatus();
                            if (status == true) {
                                ListKelas adapterKelas = new ListKelas(com.khaerul.aplikasipresensisekolah.views.KelasActivity.this, dataKelasItemList, getTahunAjaran, getJenjang);
                                rvListKelas.setAdapter(adapterKelas);
                            } else {
                                Toast.makeText(com.khaerul.aplikasipresensisekolah.views.KelasActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            Toast.makeText(com.khaerul.aplikasipresensisekolah.views.KelasActivity.this, "Data kelas belum tersedia", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else  {
                    Toast.makeText(com.khaerul.aplikasipresensisekolah.views.KelasActivity.this, "Check your connection or request API Now!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKelas> call, Throwable t) {
                Toast.makeText(com.khaerul.aplikasipresensisekolah.views.KelasActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_class, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}