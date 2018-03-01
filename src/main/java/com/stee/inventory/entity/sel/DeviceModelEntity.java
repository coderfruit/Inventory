package com.stee.inventory.entity.sel;
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
 * File Name    : DeviceModelEntity.java
 * Author       : yuxiaolin
 * Created      : 2018年02月09日 下午1:29:54
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 **/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stl_device_model")
public class DeviceModelEntity {
	/**
	 * primary key
	 */
	@Id
	@Column(name="device_model_id")
    private String deviceModelId;
	/**
	 * Description
	 */
	@Column(name="description")
    private String description;
    /**
     * Lamp Type
     */
	@Column(name="lamp_type")
    private String lampType;
    /**
     * Manufacturer
     */
	@Column(name="manufacturer")
    private String manufacturer;
    /**
     * Rated Watt
     */
	@Column(name="rated_watt")
    private Integer ratedWatt;
    /**
     * Control Protocol
     */
	@Column(name="control_protocol")
    private String controlProtocol;
    /**
     * Built-in Light Sensor
     */
	@Column(name="built_in_light_sensor")
    private boolean builtInLightSensor;
    /**
     * Built-in Motion Sensor
     */
	@Column(name="built_in_motion_sensor")
    private boolean builtInMotionSensor;
    /**
     * Picture
     */
	@Column(name="picture")
    private String picture;
    /**
     * Support Polling Method
     */
	@Column(name="polling_method")
    private boolean pollingMethod;
    /**
     * Estimated Life Time
     */
	@Column(name="life_time")
    private Integer lifeTime;
    /**
     * Tenant Id
     */
	@Column(name="tenant_id")
    private Integer tenantId;
	
    /*****************set get*******************/
	
	public String getDeviceModelId() {
		return deviceModelId;
	}

	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLampType() {
		return lampType;
	}

	public void setLampType(String lampType) {
		this.lampType = lampType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getRatedWatt() {
		return ratedWatt;
	}

	public void setRatedWatt(Integer ratedWatt) {
		this.ratedWatt = ratedWatt;
	}

	public String getControlProtocol() {
		return controlProtocol;
	}

	public void setControlProtocol(String controlProtocol) {
		this.controlProtocol = controlProtocol;
	}

	public boolean isBuiltInLightSensor() {
		return builtInLightSensor;
	}

	public void setBuiltInLightSensor(boolean builtInLightSensor) {
		this.builtInLightSensor = builtInLightSensor;
	}

	public boolean isBuiltInMotionSensor() {
		return builtInMotionSensor;
	}

	public void setBuiltInMotionSensor(boolean builtInMotionSensor) {
		this.builtInMotionSensor = builtInMotionSensor;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isPollingMethod() {
		return pollingMethod;
	}

	public void setPollingMethod(boolean pollingMethod) {
		this.pollingMethod = pollingMethod;
	}

	public Integer getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(Integer lifeTime) {
		this.lifeTime = lifeTime;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "DeviceModelEntity [deviceModelId=" + deviceModelId + ", description=" + description + ", lampType="
				+ lampType + ", manufacturer=" + manufacturer + ", ratedWatt=" + ratedWatt + ", controlProtocol="
				+ controlProtocol + ", builtInLightSensor=" + builtInLightSensor + ", builtInMotionSensor="
				+ builtInMotionSensor + ", picture=" + picture + ", pollingMethod=" + pollingMethod + ", lifeTime="
				+ lifeTime + ", tenantId=" + tenantId + "]";
	}
	
}
