package com.stee.inventory.dto;

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
	private boolean lampSwitch;

	/**
	 * Control mode: Manual/Automatic
	 */
	private ControlMode controlMode;

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

	public boolean isLampSwitch() {
		return lampSwitch;
	}

	public void setLampSwitch(boolean lampSwitch) {
		this.lampSwitch = lampSwitch;
	}

	public ControlMode getControlMode() {
		return controlMode;
	}

	public void setControlMode(ControlMode controlMode) {
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
