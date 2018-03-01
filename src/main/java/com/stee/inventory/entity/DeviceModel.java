package com.stee.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "stl_device_model")
public class DeviceModel implements Serializable{
    private String deviceModelId;
    private String description;
    private String lampType;
    private String manufacturer;
    private Integer ratedWatt;
    private String controlProtocol;
    private Boolean builtInLightSensor;
    private Boolean builtInMotionSensor;
    private byte[] picture;
    private Boolean pollingMethod;
    private Integer lifeTime;
    private Integer tenantId;

    @Id
    @Column(name = "device_model_id")
    public String getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(String deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "lamp_type")
    public String getLampType() {
        return lampType;
    }

    public void setLampType(String lampType) {
        this.lampType = lampType;
    }

    @Basic
    @Column(name = "manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Basic
    @Column(name = "rated_watt")
    public Integer getRatedWatt() {
        return ratedWatt;
    }

    public void setRatedWatt(Integer ratedWatt) {
        this.ratedWatt = ratedWatt;
    }

    @Basic
    @Column(name = "control_protocol")
    public String getControlProtocol() {
        return controlProtocol;
    }

    public void setControlProtocol(String controlProtocol) {
        this.controlProtocol = controlProtocol;
    }

    @Basic
    @Column(name = "built_in_light_sensor")
    public Boolean getBuiltInLightSensor() {
        return builtInLightSensor;
    }

    public void setBuiltInLightSensor(Boolean builtInLightSensor) {
        this.builtInLightSensor = builtInLightSensor;
    }

    @Basic
    @Column(name = "built_in_motion_sensor")
    public Boolean getBuiltInMotionSensor() {
        return builtInMotionSensor;
    }

    public void setBuiltInMotionSensor(Boolean builtInMotionSensor) {
        this.builtInMotionSensor = builtInMotionSensor;
    }

    @Basic
    @Column(name = "picture")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "polling_method")
    public Boolean getPollingMethod() {
        return pollingMethod;
    }

    public void setPollingMethod(Boolean pollingMethod) {
        this.pollingMethod = pollingMethod;
    }

    @Basic
    @Column(name = "life_time")
    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public DeviceModel(String deviceModelId, String description, String lampType, String manufacturer, Integer ratedWatt, String controlProtocol, Boolean builtInLightSensor, Boolean builtInMotionSensor, byte[] picture, Boolean pollingMethod, Integer lifeTime, Integer tenantId) {
        this.deviceModelId = deviceModelId;
        this.description = description;
        this.lampType = lampType;
        this.manufacturer = manufacturer;
        this.ratedWatt = ratedWatt;
        this.controlProtocol = controlProtocol;
        this.builtInLightSensor = builtInLightSensor;
        this.builtInMotionSensor = builtInMotionSensor;
        this.picture = picture;
        this.pollingMethod = pollingMethod;
        this.lifeTime = lifeTime;
        this.tenantId = tenantId;
    }

    public DeviceModel() {
    }
}
