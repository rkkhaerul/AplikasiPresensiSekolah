package com.khaerul.aplikasipresensisekolah.model.master;

import com.google.gson.annotations.SerializedName;

public class DataTahunajaranItem{

	@SerializedName("tahunajaran")
	private String tahunajaran;

	@SerializedName("identifikasi")
	private String identifikasi;

	@SerializedName("kode_tahun_ajaran")
	private String kodeTahunAjaran;

	public String getTahunajaran(){
		return tahunajaran;
	}

	public String getIdentifikasi(){
		return identifikasi;
	}

	public String getKodeTahunAjaran(){
		return kodeTahunAjaran;
	}
}