/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project id : asm
 * File id    : PoleQueryBean
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
    private String id;
    private String description;
    private Double heightStart;
    private Double heightEnd;
    private Integer pageSize;
    private Integer pageNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "PoleQueryBean{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", heightStart=" + heightStart +
                ", heightEnd=" + heightEnd +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
