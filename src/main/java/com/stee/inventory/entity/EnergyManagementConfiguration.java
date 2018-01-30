package com.stee.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : ocm
 * File Name    : EnergyManagementConfiguration.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月28日 下午3:47:29
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments 能源配置管理<br>
 * --------------------------------------------------------------------------------
 *  
 */
@Entity
@Table(name = "stl_ocm_emc")
public class EnergyManagementConfiguration implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;                     //配置的id
	@Column(name = "name")
	private String name;                //灯类型 的 名字
	@Column(name = "type")
	private String type;                //配置的类型，有2类，Energy Usage 和 Electrical Parameters
	@Column(name = "tracking_entity_type")
	private String trackingEntityType;  //追踪实体的类型，有3类，Luminaire, CMS-Streetlight Application, or No Tracking
	@JoinColumn(name="emc_id")
	@OrderBy("serverityLevel desc")
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Threshold> thresholds; //一系列的阈值
	@Column(name = "luminaire_tracking_capability")
	private boolean luminaireTrackingCapability; //灯具的追踪能力，是否具有自动追踪/记录/返回参数功能
	@Column(name = "value_type")
	private String valueType;           //instantaneous, RMS, average 三个中一个
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrackingEntityType() {
		return trackingEntityType;
	}
	public void setTrackingEntityType(String trackingEntityType) {
		this.trackingEntityType = trackingEntityType;
	}
	public Set<Threshold> getThresholds() {
		return thresholds;
	}
	public void setThresholds(Set<Threshold> thresholds) {
		this.thresholds = thresholds;
	}
	public boolean isLuminaireTrackingCapability() {
		return luminaireTrackingCapability;
	}
	public void setLuminaireTrackingCapability(boolean luminaireTrackingCapability) {
		this.luminaireTrackingCapability = luminaireTrackingCapability;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public EnergyManagementConfiguration(int id, String name, String type,
			String trackingEntityType, Set<Threshold> thresholds,
			boolean luminaireTrackingCapability, String valueType) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.trackingEntityType = trackingEntityType;
		this.thresholds = thresholds;
		this.luminaireTrackingCapability = luminaireTrackingCapability;
		this.valueType = valueType;
	}
	public EnergyManagementConfiguration() {
	}
	@Override
	public String toString() {
		return "EnergyManagementConfiguration [id=" + id + ", name=" + name
				+ ", type=" + type + ", trackingEntityType="
				+ trackingEntityType + ", thresholds=" + thresholds
				+ ", luminaireTrackingCapability="
				+ luminaireTrackingCapability + ", valueType=" + valueType
				+ "]";
	}
	
}
