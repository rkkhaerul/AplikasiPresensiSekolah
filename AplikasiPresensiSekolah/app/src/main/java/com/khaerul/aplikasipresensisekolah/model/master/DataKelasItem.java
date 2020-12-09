package com.khaerul.aplikasipresensisekolah.model.master;

import com.google.gson.annotations.SerializedName;

public class DataKelasItem{

	@SerializedName("kode_kelas")
	private String kodeKelas;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("deskripsi")
	private String deskripsi;

	public String getKodeKelas(){
		return kodeKelas;
	}

	public String getKelas(){
		return kelas;
	}

	public String getDeskripsi(){
		return deskripsi;
	}
}