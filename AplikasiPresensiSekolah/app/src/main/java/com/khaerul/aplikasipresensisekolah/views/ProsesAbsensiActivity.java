package com.khaerul.aplikasipresensisekolah.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.khaerul.aplikasipresensisekolah.R;
import com.khaerul.aplikasipresensisekolah.adapter.FilterClass;
import com.khaerul.aplikasipresensisekolah.adapter.InRombel;
import com.khaerul.aplikasipresensisekolah.model.filter.DatasiswaItem;
import com.khaerul.aplikasipresensisekolah.model.filter.ResponseRombel;
import com.khaerul.aplikasipresensisekolah.model.inrombel.ResponseInRombel;
import com.khaerul.aplikasipresensisekolah.model.inrombel.SiswainrombelItem;
import com.khaerul.aplikasipresensisekolah.network.ApiServices;
import com.khaerul.aplikasipresensisekolah.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProsesAbsensiActivity extends AppCompatActivity {

    RecyclerView rvListSiswaFilter;
    String kodeTahunAjaran, kodeJenjang, kodeKelas, kodeJurusan;
    TextView txtNotFound;

    String totalsiswa, kelasId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses_absensi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_tp));

        rvListSiswaFilter = (RecyclerView)findViewById(R.id.listSiswaFilter);
        rvListSiswaFilter.setHasFixedSize(true);
        rvListSiswaFilter.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        txtNotFound = (TextView)findViewById(R.id.txtDataSiswaTidakTersedia);

        if (getIntent().getExtras() != null) {
            Bundle getPackage = getIntent().getExtras();
            kodeTahunAjaran = getPackage.getString("TA");
            kodeJenjang = getPackage.getString("JJG");
            kodeKelas = getPackage.getString("CLS");
            kodeJurusan = getPackage.getString("JRSN");

            if (kodeKelas.equals("X")) {
                kelasId = "10";
            } else if (kodeKelas.equals("XI")) {
                kelasId = "11";
            } else if (kodeKelas.equals("XII")) {
                kelasId = "12";
            }

            getSupportActionBar().setTitle("Kelas " + kelasId);


            switch (kodeJurusan) {
                case "ALLDATA":
                    inRombel(kodeTahunAjaran, kodeKelas);
                    getSupportActionBar().setSubtitle("Semua Datasiswa");
                    break;
                default:
                    showdataSiswa(kodeTahunAjaran, kodeKelas, kodeJurusan);
                    getSupportActionBar().setSubtitle("Jurusan " + kodeJurusan);
                    break;
            }
        }
    }

    private void inRombel(String kodeTahunAjaran, String kodeKelas) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseInRombel> inRombelCall = apiServices.inRombel(kodeTahunAjaran, kodeKelas);

        inRombelCall.enqueue(new Callback<ResponseInRombel>() {
            @Override
            public void onResponse(Call<ResponseInRombel> call, Response<ResponseInRombel> response) {
                if (response.isSuccessful()) {
                    totalsiswa = response.body().getTotalsiswa() + " Siswa";

                    List<SiswainrombelItem> siswainrombelItems = response.body().getSiswainrombel();
                    boolean status = response.body().isStatus();

                    if (status) {

                        InRombel adapterinRombel = new InRombel(com.khaerul.aplikasipresensisekolah.views.ProsesAbsensiActivity.this, siswainrombelItems, kodeTahunAjaran);
                        rvListSiswaFilter.setAdapter(adapterinRombel);
                    } else {
                        totalsiswa = "0 Siswa";

                        txtNotFound.setVisibility(View.VISIBLE);
                        txtNotFound.setText(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseInRombel> call, Throwable t) {

            }
        });
    }


    private void showdataSiswa(String kodeTahunAjaran, String kodeKelas, String kodeJurusan) {
        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseRombel> datasiswaFilterCall = apiServices.datasiswaFilter(kodeTahunAjaran, kodeKelas, kodeJurusan);

        datasiswaFilterCall.enqueue(new Callback<ResponseRombel>() {
            @Override
            public void onResponse(Call<ResponseRombel> call, Response<ResponseRombel> response) {
                if (response.isSuccessful()) {
                    totalsiswa = response.body().getTotalsiswa() + " Siswa";

                    List<DatasiswaItem> datasiswaItems = response.body().getDatasiswa();
                    boolean status = response.body().isStatus();

                    if (status == true) {

                        FilterClass adapterFilterClass = new FilterClass(com.khaerul.aplikasipresensisekolah.views.ProsesAbsensiActivity.this, datasiswaItems, kodeTahunAjaran);
                        rvListSiswaFilter.setAdapter(adapterFilterClass);
                    } else {
                        totalsiswa = "0 Siswa";

                        txtNotFound.setVisibility(View.VISIBLE);
                        txtNotFound.setText(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRombel> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_itemdatasiswa, menu);

        menu.findItem(R.id.item_menu).setTitle(totalsiswa);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.item_menu);
        return super.onPrepareOptionsMenu(menu);
    }

}