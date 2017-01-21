package TEEEEEEEEEEEEEEEEEEEEEEEEEEEEST;

public class Location {
	
	private String name;
	private float latitude;
	private float longitude;
	private String type;
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Location() {
	}
	
	@Override
	public String toString() {
		return "Location [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", type=" + type
				+ "]";
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
