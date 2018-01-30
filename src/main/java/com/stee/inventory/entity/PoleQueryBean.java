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
 * File Name    : PoleQueryBean
 * Author       : Jerry
 * Created      : 2017/1/9
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
package com.stee.inventory.entity;

/**
 * Created by SerryMiano on 2017/1/9.
 */
public class PoleQueryBean {
    private String name;
    private String description;
    private Double heightStart;
    private Double heightEnd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHeightStart() {
        return heightStart;
    }

    public void setHeightStart(Double heightStart) {
        this.heightStart = heightStart;
    }

    public Double getHeightEnd() {
        return heightEnd;
    }

    public void setHeightEnd(Double heightEnd) {
        this.heightEnd = heightEnd;
    }

    @Override
    public String toString() {
        return "PoleQueryBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", heightStart=" + heightStart +
                ", heightEnd=" + heightEnd +
                '}';
    }
}
