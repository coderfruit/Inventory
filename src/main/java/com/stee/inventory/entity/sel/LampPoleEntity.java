package com.stee.inventory.entity.sel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
 * File Name    : LampPoleEntity.java
 * Author       : yuxiaolin
 * Created      : 2018年02月06日 下午1:29:54
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Entity
@Table(name = "stl_lamp_pole")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LampPoleEntity {

	/**
	 * Lamp Pole ID
	 */
	@Id
	@Column(name="lamp_pole_id")
	private String lampPoleId;

	/**
	 * Lamp Pole Model ID
	 */
	@Column(name="lamp_pole_model_id")
	private String lampPoleModelId;
	/**
	 * tenant id
	 */
	@Column(name="tenant_id")
	private Integer tenantId;

	public LampPoleEntity() {
		super();
	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public LampPoleEntity(@JsonProperty("lampPoleId") String lampPoleId, @JsonProperty("lampPoleModelId") String lampPoleModelId) {
		super();
		this.lampPoleId = lampPoleId;
		this.lampPoleModelId = lampPoleModelId;
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
	
	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "LampPoleEntity [lampPoleId=" + lampPoleId + ", lampPoleModelId=" + lampPoleModelId + ", tenantId="
				+ tenantId + "]";
	}

}
