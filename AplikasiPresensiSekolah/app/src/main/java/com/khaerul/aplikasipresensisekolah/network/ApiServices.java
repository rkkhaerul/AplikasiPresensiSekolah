package com.khaerul.aplikasipresensisekolah.network;

import com.khaerul.aplikasipresensisekolah.model.filter.ResponseRombel;
import com.khaerul.aplikasipresensisekolah.model.inrombel.ResponseInRombel;
import com.khaerul.aplikasipresensisekolah.model.master.ResponseJenjang;
import com.khaerul.aplikasipresensisekolah.model.master.ResponseKelas;
import com.khaerul.aplikasipresensisekolah.model.master.ResponseTahunAjaran;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("module/absensi/listsiswa_inreq.php")
    Call<ResponseRombel> datasiswaFilter (
        @Field("tahunajaran") String ta,
        @Field("kode_kelas") String kelas,
        @Field("kode_jurusan") String jurusan
    );

    @FormUrlEncoded
    @POST("module/absensi/listsiswa_inreq.php")
    Call<ResponseInRombel> inRombel (
        @Field("tahunajaran") String ta,
        @Field("kode_kelas") String kelas
    );

    @GET("module/master/datatahunajaran.php")
    Call<ResponseTahunAjaran> getTahunAjaran();

    @GET("module/master/datajenjang.php")
    Call<ResponseJenjang> getJenjang();

    @GET("module/master/datakelas.php")
    Call<ResponseKelas> getKelas();
}
