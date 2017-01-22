package com.millesBornes.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.millesBornes.web.jsonview.Views;

public class Move {
	@JsonView(Views.Public.class)
	double lat;
	
	@JsonView(Views.Public.class)
	double lng;
	
	@JsonView(Views.Public.class)
	double distance;
	
	@JsonView(Views.Public.class)
	int radius;
	
	@JsonView(Views.Public.class)
	int idPlayer;
	
	@JsonView(Views.Public.class)
	String proche;
	
	public String getProche() {
		return proche;
	}

	public void setProche(String proche) {
		this.proche = proche;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getLng() {
		return lng;
	}
	
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
}
