package com.stee.inventory.entity;

import com.stee.sel.constant.ControlMode;

import java.util.Date;

public class DeviceControl {


	/**
	 * Unique Code
	 */
	private String deviceId;

	/**
	 * Dimming of lamp,range of [0 - 100]
	 */
	private Integer lampLevel;

	/**
	 * Lamp [On/Off]
	 */
	private Boolean lampSwitch;

	/**
	 * Control mode: Manual/Automatic
	 */
	private String controlMode;

	private Date commissionDate;
	
	private String controlProtocol;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getLampLevel() {
		return lampLevel;
	}

	public void setLampLevel(Integer lampLevel) {
		this.lampLevel = lampLevel;
	}

	public Boolean isLampSwitch() {
		return lampSwitch;
	}

	public void setLampSwitch(Boolean lampSwitch) {
		this.lampSwitch = lampSwitch;
	}

	public String getControlMode() {
		return controlMode;
	}

	public void setControlMode(String controlMode) {
		this.controlMode = controlMode;
	}

	public Date getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(Date commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getControlProtocol() {
		return controlProtocol;
	}

	public void setControlProtocol(String controlProtocol) {
		this.controlProtocol = controlProtocol;
	}
	
	
}
