package com.stee.inventory.dto;

public class DeviceInfo {
	
	private String deviceName;
	private String deviceId;
	private String luminaireModelId;
	private String lampPoleId;
	private String lampPoleModelId;
	private String installedDate;
	private String picStr;
	
	public void setDeviceInfo(String deviceName, String deviceId, String luminaireModelId, String lampPoleId,
			String lampPoleModelId, String installedDate, String picStr) {
		this.deviceName = deviceName;
		this.deviceId = deviceId;
		this.luminaireModelId = luminaireModelId;
		this.lampPoleId = lampPoleId;
		this.lampPoleModelId = lampPoleModelId;
		this.installedDate = installedDate;
		this.picStr = picStr;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getLuminaireModelId() {
		return luminaireModelId;
	}
	public void setLuminaireModelId(String luminaireModelId) {
		this.luminaireModelId = luminaireModelId;
	}
	public String getLampPoleId() {
		return lampPoleId;
	}
	public void setLampPoleId(String lampPoleId) {
		this.lampPoleId = lampPoleId;
	}
	public String getLampPoleModelId() {
		return lampPoleModelId;
	}
	public void setLampPoleModelId(String lampPoleModelId) {
		this.lampPoleModelId = lampPoleModelId;
	}
	public String getPicStr() {
		return picStr;
	}
	public void setPicStr(String picStr) {
		this.picStr = picStr;
	}
	public String getInstalledDate() {
		return installedDate;
	}
	public void setInstalledDate(String installedDate) {
		this.installedDate = installedDate;
	}
	@Override
	public String toString() {
		return "DeviceInfo [deviceName=" + deviceName + ", deviceId=" + deviceId + ", luminaireModelId="
				+ luminaireModelId + ", lampPoleId=" + lampPoleId + ", lampPoleModelId=" + lampPoleModelId
				+ ", installedDate=" + installedDate + ", picStr=" + picStr + "]";
	}
	
	
}
