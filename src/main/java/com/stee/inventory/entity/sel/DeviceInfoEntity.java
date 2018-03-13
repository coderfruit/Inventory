package com.stee.inventory.entity.sel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.stee.sel.constant.ControlMode;
import com.stee.sel.constant.OperationState;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_SEL
 * File Name    : LampInfo.java
 * Author       : Jerry
 * Created      : 2016年10月13日 下午1:31:16
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
/**
 * <pre>
 * Project Name : STL_SEL
 * File Name    : DeviceInfoEntity.java
 * Author       : yuxiaolin
 * Created      : 2018年02月06日 下午4:10:18
 * </pre>
 */

@Entity
@Table(name = "stl_device")
public class DeviceInfoEntity {

	/**
	 * Device ID (Must be Unique)
	 */
	@Transient
	private String lampPoleModelId;
	@Id
	@Column(name = "device_id")
	private String deviceId;
	/**
	 * Device Name
	 */
	@Column(name = "name")
	private String name;
	/**
	 * Geo Zone Id
	 */
	@Column(name = "geozone_id")
	private Integer geozoneId;
	/**
	 * country
	 */
	@Column(name = "country")
	private String country;
	/**
	 * state
	 */
	@Column(name = "state")
	private String state;
	/**
	 * street
	 */
	@Column(name = "street")
	private String street;
	/**
	 * Longitude
	 */
	@Column(name = "longitude")
	private Double longitude;

	/**
	 * Latitude
	 */
	@Column(name = "latitude")
	private Double latitude;
	/**
	 * Control Mode
	 */
	@Column(name = "control_mode")
	@Enumerated(EnumType.STRING)
	private ControlMode controlMode;
	/**
	 * Dimming Group Id
	 */
	@Column(name = "dimming_group_id")
	private Integer dimmingGroupId;
	/**
	 * Dimming Level
	 */
	@Column(name = "dimming_level")
	private Integer dimmingLevel;
	/**
	 * Operation State
	 */
	@Column(name = "operation_state")
	@Enumerated(EnumType.STRING)
	private OperationState operationState;
	/**
	 * Burning Hour
	 */
	@Column(name = "burning_hour")
	private Integer burningHour;
	/**
	 * Energy Usage
	 */
	@Column(name = "energy_usage")
	private Double energyUsage;
	/**
	 * Installation Date
	 */
	@Column(name = "installation_date")
	private Date installationDate;
	/**
	 * Level Feedback
	 */
	@Column(name = "level_feedback")
	private Integer levelFeedback;
	/**
	 * Switch Feedback
	 */
	@Column(name = "switch_feedback")
	private Boolean switchFeedback;
	
	/**
	 * Current Flow
	 */
	@Column(name = "current_flow")
	private Double currentFlow;
	/**
	 * voltage
	 */
	@Column(name = "voltage")
	private Double voltage;
	/**
	 * Active Power
	 */
	@Column(name = "active_power")
	private Double activePower;
	/**
	 * Reactive Power
	 */
	@Column(name = "reactive_power")
	private Double reactivePower;
	/**
	 * Apparent Power
	 */
	@Column(name = "apparent_power")
	private Double apparentPower;
	/**
	 *  Power Factor
	 */
	@Column(name = "power_factor")
	private Double powerFactor;
	/**
	 *  Temperature
	 */
	@Column(name = "temperature")
	private Double temperature;
	/**
	 *  Last Updated Time
	 */
	@Transient
	private Double lastUpdatedTime;
	/**
	 *  Tenant Id
	 */
	@Column(name = "tenant_id")
	private Integer TenantId;
	/**
	 * <pre>
	 * Lamp Pole Info :
	 *  a.	Lamp Pole ID
	 *	b.	Lamp Pole Model ID
	 * </pre>
	 */
	@Column(name="lamp_pole_id")
	private String lampPoleId;
	/**
	 * Device Model Id
	 */
	@Column(name="device_model_id")
	private String deviceModelId;


	public String getLampPoleModelId() {
		return lampPoleModelId;
	}

	public void setLampPoleModelId(String lampPoleModelId) {
		this.lampPoleModelId = lampPoleModelId;
	}

	public Boolean getSwitchFeedback() {
		return switchFeedback;
	}

	/******************set get*******************/

	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getGeozoneId() {
		return geozoneId;
	}
	public void setGeozoneId(Integer geozoneId) {
		this.geozoneId = geozoneId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public ControlMode getControlMode() {
		return controlMode;
	}
	public void setControlMode(ControlMode controlMode) {
		this.controlMode = controlMode;
	}
	public Integer getDimmingGroupId() {
		return dimmingGroupId;
	}
	public void setDimmingGroupId(Integer dimmingGroupId) {
		this.dimmingGroupId = dimmingGroupId;
	}
	public Integer getDimmingLevel() {
		return dimmingLevel;
	}
	public void setDimmingLevel(Integer dimmingLevel) {
		this.dimmingLevel = dimmingLevel;
	}
	public OperationState getOperationState() {
		return operationState;
	}
	public void setOperationState(OperationState operationState) {
		this.operationState = operationState;
	}
	public Integer getBurningHour() {
		return burningHour;
	}
	public void setBurningHour(Integer burningHour) {
		this.burningHour = burningHour;
	}
	public Double getEnergyUsage() {
		return energyUsage;
	}
	public void setEnergyUsage(Double energyUsage) {
		this.energyUsage = energyUsage;
	}
	public Date getInstallationDate() {
		return installationDate;
	}
	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}
	public Integer getLevelFeedback() {
		return levelFeedback;
	}
	public void setLevelFeedback(Integer levelFeedback) {
		this.levelFeedback = levelFeedback;
	}
	public Boolean isSwitchFeedback() {
		return switchFeedback;
	}
	public void setSwitchFeedback(Boolean switchFeedback) {
		this.switchFeedback = switchFeedback;
	}
	public Double getCurrentFlow() {
		return currentFlow;
	}
	public void setCurrentFlow(Double currentFlow) {
		this.currentFlow = currentFlow;
	}
	public Double getVoltage() {
		return voltage;
	}
	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}
	public Double getActivePower() {
		return activePower;
	}
	public void setActivePower(Double activePower) {
		this.activePower = activePower;
	}
	public Double getReactivePower() {
		return reactivePower;
	}
	public void setReactivePower(Double reactivePower) {
		this.reactivePower = reactivePower;
	}
	public Double getApparentPower() {
		return apparentPower;
	}
	public void setApparentPower(Double apparentPower) {
		this.apparentPower = apparentPower;
	}
	public Double getPowerFactor() {
		return powerFactor;
	}
	public void setPowerFactor(Double powerFactor) {
		this.powerFactor = powerFactor;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Double lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public Integer getTenantId() {
		return TenantId;
	}
	public void setTenantId(Integer tenantId) {
		TenantId = tenantId;
	}
	public String getLampPoleId() {
		return lampPoleId;
	}
	public void setLampPoleId(String lampPoleId) {
		this.lampPoleId = lampPoleId;
	}
	public String getDeviceModelId() {
		return deviceModelId;
	}
	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}

}
