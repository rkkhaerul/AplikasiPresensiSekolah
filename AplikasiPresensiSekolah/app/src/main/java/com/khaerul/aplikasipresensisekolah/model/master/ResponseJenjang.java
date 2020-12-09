package com.khaerul.aplikasipresensisekolah.model.master;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseJenjang{

	@SerializedName("data_jenjang")
	private List<com.khaerul.aplikasipresensisekolah.model.master.DataJenjangItem> dataJenjang;

	@SerializedName("total_datajenjang")
	private String totalDatajenjang;

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<com.khaerul.aplikasipresensisekolah.model.master.DataJenjangItem> getDataJenjang(){
		return dataJenjang;
	}

	public String getTotalDatajenjang(){
		return totalDatajenjang;
	}

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}