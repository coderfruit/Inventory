/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : lim
 * File Name    : Luminaire
 * Author       : Jerry
 * Created      : 2017/1/10
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
package com.stee.inventory.dto;

/**
 * Created by SerryMiano on 2017/1/10.
 */
public class Luminaire {
    private Integer id;

    private String modelId;

    private String description;

    private String lampType;

    private Double ratedWatt;

    private String manufacturer;

    private boolean lightSensor;

    private boolean motionSensor;

    private boolean pollingMethod;

    private String controlProtocol;

    private Integer lifeTime;

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
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

    public Double getRatedWatt() {
        return ratedWatt;
    }

    public void setRatedWatt(Double ratedWatt) {
        this.ratedWatt = ratedWatt;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isLightSensor() {
        return lightSensor;
    }

    public void setLightSensor(boolean lightSensor) {
        this.lightSensor = lightSensor;
    }

    public boolean isMotionSensor() {
        return motionSensor;
    }

    public void setMotionSensor(boolean motionSensor) {
        this.motionSensor = motionSensor;
    }

    public boolean isPollingMethod() {
        return pollingMethod;
    }

    public void setPollingMethod(boolean pollingMethod) {
        this.pollingMethod = pollingMethod;
    }

    public String getControlProtocol() {
        return controlProtocol;
    }

    public void setControlProtocol(String controlProtocol) {
        this.controlProtocol = controlProtocol;
    }

    @Override
    public String toString() {
        return "Luminaire{" +
                "id=" + id +
                ", modelId='" + modelId + '\'' +
                ", description='" + description + '\'' +
                ", lampType='" + lampType + '\'' +
                ", ratedWatt=" + ratedWatt +
                ", manufacturer='" + manufacturer + '\'' +
                ", lightSensor=" + lightSensor +
                ", motionSensor=" + motionSensor +
                ", pollingMethod=" + pollingMethod +
                ", controlProtocol='" + controlProtocol + '\'' +
                ", lifeTime=" + lifeTime +
                '}';
    }
}
