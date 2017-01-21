package com.millesBornes.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class Batiment {

	@JsonView(Views.Public.class)
	String lon;
	@JsonView(Views.Public.class)
	String lat;
	
	public Batiment(String lon, String lat){
		this.lon=lon;
		this.lat=lat;
	}
	
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	
	
	
}
