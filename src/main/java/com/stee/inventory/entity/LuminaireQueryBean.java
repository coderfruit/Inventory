/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : asm
 * File Name    : LuminaireQueryBean
 * Author       : Jerry
 * Created      : 2017/1/6
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
package com.stee.inventory.entity;

/**
 * Created by SerryMiano on 2017/1/6.
 */
public class LuminaireQueryBean {
    private String modelId;
    private String description;
    private String manufacturer;
    private String lampType;
    private String controlProtocol;
    private Double ratedWattStart;
    private Double ratedWattEnd;
    private Integer pageSize;
    private Integer pageNum;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLampType() {
        return lampType;
    }

    public void setLampType(String lampType) {
        this.lampType = lampType;
    }

    public String getControlProtocol() {
        return controlProtocol;
    }

    public void setControlProtocol(String controlProtocol) {
        this.controlProtocol = controlProtocol;
    }

    public Double getRatedWattStart() {
        return ratedWattStart;
    }

    public void setRatedWattStart(Double ratedWattStart) {
        this.ratedWattStart = ratedWattStart;
    }

    public Double getRatedWattEnd() {
        return ratedWattEnd;
    }

    public void setRatedWattEnd(Double ratedWattEnd) {
        this.ratedWattEnd = ratedWattEnd;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "LuminaireQueryBean{" +
                "modelId='" + modelId + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", lampType='" + lampType + '\'' +
                ", controlProtocol='" + controlProtocol + '\'' +
                ", ratedWattStart=" + ratedWattStart +
                ", ratedWattEnd=" + ratedWattEnd +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
