package com.stee.inventory.entity;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_LIM
 * File Name    : GeoZoneLampInfo.java
 * Author       : Jerry
 * Created      : 2016年11月14日 下午3:34:55
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public class GeoZoneLampInfo {
	private String deviceId;
	private Double latitude;
	private Double longitude;
	
	public GeoZoneLampInfo() {
		super();
	}
	
	public GeoZoneLampInfo(String deviceId, Double latitude, Double longitude) {
		super();
		this.deviceId = deviceId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	@Override
	public String toString() {
		return "GeoZoneLampInfo [deviceId=" + deviceId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}


}
