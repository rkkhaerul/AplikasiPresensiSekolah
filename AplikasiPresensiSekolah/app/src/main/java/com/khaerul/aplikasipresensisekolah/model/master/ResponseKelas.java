package com.khaerul.aplikasipresensisekolah.model.master;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKelas{

	@SerializedName("code")
	private int code;

	@SerializedName("total_datakelas")
	private String totalDatakelas;

	@SerializedName("message")
	private String message;

	@SerializedName("data_kelas")
	private List<DataKelasItem> dataKelas;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public String getTotalDatakelas(){
		return totalDatakelas;
	}

	public String getMessage(){
		return message;
	}

	public List<DataKelasItem> getDataKelas(){
		return dataKelas;
	}

	public boolean isStatus(){
		return status;
	}
}