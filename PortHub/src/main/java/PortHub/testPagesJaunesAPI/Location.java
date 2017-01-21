package PortHub.testPagesJaunesAPI;

public class Location {
	
	private String name;
	private float latitude;
	private float longitude;
	
	public Location() {
	}
	
	
	@Override
	public String toString() {
		return "Location [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
