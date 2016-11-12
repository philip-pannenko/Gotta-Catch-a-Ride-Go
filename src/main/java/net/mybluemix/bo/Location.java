package net.mybluemix.bo;

public class Location {
	private float latitude;
	private float longitude;
	private int accuracy;
	private long id;
	private String message;

	public Location() {
		super();
	}

	public Location(float latitude, float longitude, int accuracy, long id, String message) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.id = id;
		this.message = message;
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

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + ", accuracy=" + accuracy + ", id=" + id
				+ ", message=" + message + "]";
	}

}
