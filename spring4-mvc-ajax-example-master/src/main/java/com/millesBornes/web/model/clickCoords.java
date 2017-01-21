package com.millesBornes.web.model;

public class clickCoords {
	String lat;
	String lng;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setEmail(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "SearchCriteria [longitude=" + lng + ", latitude=" + lat + "]";
	}
}
