package com.stee.inventory.entity;

public class DeviceLocationInfo {
	private String deviceId;
	private Integer geoZone;
	private Double latitude;
	private Double longitude;
	private String country;
	private String city;
	private String address;


	public void setDeviceLocationInfo(Integer geoZone, Double latitude, Double longitude, String country, String city,
			String address) {
		this.geoZone = geoZone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
		this.city = city;
		this.address = address;
	}
	public Integer getGeoZone() {
		return geoZone;
	}
	public void setGeoZone(Integer geoZone) {
		this.geoZone = geoZone;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Override
	public String toString() {
		return "DeviceLocationInfo [deviceId=" + deviceId + ", geoZone=" + geoZone + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", country=" + country + ", city=" + city + ", address=" + address + "]";
	}

}
