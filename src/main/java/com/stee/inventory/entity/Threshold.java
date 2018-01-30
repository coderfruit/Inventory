package com.stee.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;

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
 * File Name    : Threshold.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月28日 下午3:53:11
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments 一系列的阈值<br>
 * --------------------------------------------------------------------------------
 *  
 */
@Entity
@Table(name = "stl_ocm_threshold")
public class Threshold implements Serializable{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;                               //编号
	@Column(name = "name")                       
	private String name;                          //名称
	@Column(name = "th_value")
	private String thValue;                      //定义的值
	@Column(name = "alert_message")
	private String alertMessage;                  //警告的内容
	@Column(name = "serverity_level")
	private Integer serverityLevel;               //严重 级别
	@Column(name="less_alert_message")
	private String lessAlertMessage;              //当低于阈值时，提示信息
	@Column(name="less_serverity_level")
	private Integer lessServerityLevel;            //低于阈值时严重级别
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
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public Integer getServerityLevel() {
		return serverityLevel;
	}
	public void setServerityLevel(Integer serverityLevel) {
		this.serverityLevel = serverityLevel;
	}
	public String getLessAlertMessage() {
		return lessAlertMessage;
	}
	public void setLessAlertMessage(String lessAlertMessage) {
		this.lessAlertMessage = lessAlertMessage;
	}
	public Integer getLessServerityLevel() {
		return lessServerityLevel;
	}
	public void setLessServerityLevel(Integer lessServerityLevel) {
		this.lessServerityLevel = lessServerityLevel;
	}
	public Threshold() {
	}
	public String getThValue() {
		return thValue;
	}
	public void setThValue(String thValue) {
		this.thValue = thValue;
	}
	public Threshold(int id, String name, String thValue, String alertMessage,
			Integer serverityLevel, String lessAlertMessage,
			Integer lessServerityLevel) {
		this.id = id;
		this.name = name;
		this.thValue = thValue;
		this.alertMessage = alertMessage;
		this.serverityLevel = serverityLevel;
		this.lessAlertMessage = lessAlertMessage;
		this.lessServerityLevel = lessServerityLevel;
	}
	@Override
	public String toString() {
		return "Threshold [id=" + id + ", name=" + name + ", thValue="
				+ thValue + ", alertMessage=" + alertMessage
				+ ", serverityLevel=" + serverityLevel + ", lessAlertMessage="
				+ lessAlertMessage + ", lessServerityLevel="
				+ lessServerityLevel + "]";
	}
	
}
