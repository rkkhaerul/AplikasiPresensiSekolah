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
import com.khaerul.aplikasipresensisekolah.adapter.ListJenjang;
import com.khaerul.aplikasipresensisekolah.model.master.DataJenjangItem;
import com.khaerul.aplikasipresensisekolah.model.master.ResponseJenjang;
import com.khaerul.aplikasipresensisekolah.network.ApiServices;
import com.khaerul.aplikasipresensisekolah.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JenjangActivity extends AppCompatActivity {

    TextView txtInformasiHeader;
    RecyclerView rvListJenjang;
    String getTahunAjaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenjang);

        Toolbar toolbarTA = (Toolbar) findViewById(R.id.toolbarJenjang);
        setSupportActionBar(toolbarTA);
        getSupportActionBar().setTitle("Set Level");

        toolbarTA.setNavigationIcon(getResources().getDrawable(R.drawable.ic_tp));

        if (getIntent().getExtras() != null) {
            Bundle getPackage = getIntent().getExtras();
            getTahunAjaran = getPackage.getString("THN_AJARAN");

            getSupportActionBar().setSubtitle("TA. " + getTahunAjaran);
        }

        rvListJenjang = (RecyclerView)findViewById(R.id.rvListJenjang);

        rvListJenjang.setHasFixedSize(true);
        rvListJenjang.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        showListJenjang();
    }

    private void showListJenjang() {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseJenjang> dataJenjang = apiServices.getJenjang();

        dataJenjang.enqueue(new Callback<ResponseJenjang>() {
            @Override
            public void onResponse(Call<ResponseJenjang> call, Response<ResponseJenjang> response) {
                if (response.isSuccessful()) {
                    List<DataJenjangItem> jenjangItems = response.body().getDataJenjang();
                    boolean status = response.body().isStatus();
                    if (status == true) {
                        ListJenjang adapterJenjang = new ListJenjang(com.khaerul.aplikasipresensisekolah.views.JenjangActivity.this, jenjangItems, getTahunAjaran);
                        rvListJenjang.setAdapter(adapterJenjang);
                    } else {
                        Toast.makeText(com.khaerul.aplikasipresensisekolah.views.JenjangActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseJenjang> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_jenjang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}