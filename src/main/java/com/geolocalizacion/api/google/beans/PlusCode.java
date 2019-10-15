package com.geolocalizacion.api.google.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlusCode {
	
	private String compound_code;
	private String global_code;
	public String getCompound_code() {
		return compound_code;
	}
	public void setCompound_code(String compound_code) {
		this.compound_code = compound_code;
	}
	public String getGlobal_code() {
		return global_code;
	}
	public void setGlobal_code(String global_code) {
		this.global_code = global_code;
	}
	
	@Override
	public String toString() {
		ObjectMapper mp = new ObjectMapper();
		try {
			return mp.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
