package com.geolocalizacion.api.google.beans;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Results {
	
	private String formatted_address;
	private List<AddresComponent> address_components;
	private Geometry geometry;
	private String place_id;
	private PlusCode plus_code;
	private List<String> types;
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	public List<AddresComponent> getAddress_components() {
		return address_components;
	}
	public void setAddress_components(List<AddresComponent> address_components) {
		this.address_components = address_components;
	}
	public Geometry getGeometry() {
		return geometry;
	}
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public PlusCode getPlus_code() {
		return plus_code;
	}
	public void setPlus_code(PlusCode plus_code) {
		this.plus_code = plus_code;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
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
