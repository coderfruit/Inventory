package com.stee.inventory.service.impl;

import com.stee.sel.common.ResultData;
import com.stee.inventory.entity.EnergyManagementConfiguration;
import com.stee.inventory.repository.IEnergyManagementConfigurationDao;
import com.stee.inventory.service.ISvcEnergyManagementConfiguration;
import com.stee.inventory.utils.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
 * File Name    : SvcEnergyManagementConfigurationImpl.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午4:04:56
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service("iSvcEnergyManagementConfiguration")
public class SvcEnergyManagementConfigurationImpl implements ISvcEnergyManagementConfiguration{
	
	@Autowired@Qualifier("iEnergyManagementConfigurationDao")
	private IEnergyManagementConfigurationDao iEnergyManagementConfigurationDao;
	
	@Override
	public ResultData save(EnergyManagementConfiguration carboDioxide) {
		try{
			iEnergyManagementConfigurationDao.save(carboDioxide);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData update(EnergyManagementConfiguration carboDioxide) {
		try{
			iEnergyManagementConfigurationDao.update(carboDioxide);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData deleteEMC(Integer id) {
		try{
			iEnergyManagementConfigurationDao.deleteEMC(id);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData getEMCById(Integer id) {
		try{
			return ConfigUtil.okOnlyOne(iEnergyManagementConfigurationDao.getEMCById(id));
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData getAllEMC() {
		try{
			return ConfigUtil.okList(iEnergyManagementConfigurationDao.getAllEMC());
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData fuzzyQueryByName(String name) {
		StringBuffer sql = new StringBuffer("%").append(name).append("%");
		return ConfigUtil.okList(iEnergyManagementConfigurationDao.fuzzyQueryByName(sql.toString()));
	}
	
}
