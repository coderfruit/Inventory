package com.stee.inventory.service;

import com.stee.sel.common.ResultData;
import com.stee.inventory.entity.EnergyManagementConfiguration;
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
 * File Name    : ISvcEnergyManagementConfiguration.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:30:47
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public interface ISvcEnergyManagementConfiguration {
	public ResultData save(EnergyManagementConfiguration carboDioxide);
	
	public ResultData update(EnergyManagementConfiguration carboDioxide);
	
	public ResultData deleteEMC(Integer id);
	
	public ResultData getEMCById(Integer id);
	
	public ResultData getAllEMC();
	
	public ResultData fuzzyQueryByName(String name);
}
