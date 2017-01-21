package com.millesBornes.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class Coords {
	@JsonView(Views.Public.class)
	String lat;
	
	@JsonView(Views.Public.class)
	String lng;
	
	public String getLng() {
		return lng;
	}
	
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String getLat() {
		return lat;
	}
	
	public void setLat(String lat) {
		this.lat = lat;
	}
}
