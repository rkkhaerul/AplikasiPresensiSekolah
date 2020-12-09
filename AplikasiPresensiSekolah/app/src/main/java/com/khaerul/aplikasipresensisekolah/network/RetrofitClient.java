package com.khaerul.aplikasipresensisekolah.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://aplikasipresensi.000webhostapp.com/presensi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }

    public static com.khaerul.aplikasipresensisekolah.network.ApiServices getInstance() {
        return getRetrofit().create(com.khaerul.aplikasipresensisekolah.network.ApiServices.class);
    }
}
