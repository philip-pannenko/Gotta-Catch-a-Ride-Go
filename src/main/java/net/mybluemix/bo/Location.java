package net.mybluemix.bo;

import java.util.Date;

public class Location {
	private float latitude;
	private float longitude;
	private int accuracy;
	private long id = -1;
	private String message;
	private Date age;

	public Location() {
		super();
	}

	public Location(float latitude, float longitude, int accuracy, long id, String message, Date age) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.accuracy = accuracy;
		this.id = id;
		this.message = message;
		this.age = age;
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

	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Location [latitude=" + latitude + ", longitude=" + longitude + ", accuracy=" + accuracy + ", id=" + id
				+ ", message=" + message + ", age=" + age + "]";
	}

}
