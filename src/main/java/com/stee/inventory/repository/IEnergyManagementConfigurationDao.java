package com.stee.inventory.repository;

import com.stee.inventory.entity.EnergyManagementConfiguration;

import java.util.List;

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
 * File Name    : IEnergyManagementConfigurationDao.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:06:05
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *
 */

public interface IEnergyManagementConfigurationDao {
	
	public void save(EnergyManagementConfiguration energyManagementConfiguration);
	
	public void update(EnergyManagementConfiguration energyManagementConfiguration);
	
	public void deleteEMC(Integer id);
	
	public EnergyManagementConfiguration getEMCById(Integer id);
	
	public List<EnergyManagementConfiguration> getAllEMC();
	
	public List<EnergyManagementConfiguration> fuzzyQueryByName(String name);
}
