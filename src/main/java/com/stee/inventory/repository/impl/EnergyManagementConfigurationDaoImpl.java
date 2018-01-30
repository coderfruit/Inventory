package com.stee.inventory.repository.impl;

import com.stee.inventory.entity.EnergyManagementConfiguration;
import com.stee.inventory.repository.BaseDao;
import com.stee.inventory.repository.IEnergyManagementConfigurationDao;
import org.springframework.stereotype.Repository;

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
 * File Name    : EnergyManagementConfigurationDaoImpl.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:12:15
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *
 */

@Repository("iEnergyManagementConfigurationDao")
public class EnergyManagementConfigurationDaoImpl extends BaseDao implements IEnergyManagementConfigurationDao{

	@Override
	public void save(EnergyManagementConfiguration energyManagementConfiguration) {
		getCurrentSession().save(energyManagementConfiguration);
	}

	@Override
	public void update(
			EnergyManagementConfiguration energyManagementConfiguration) {
		getCurrentSession().update(energyManagementConfiguration);
	}

	@Override
	public void deleteEMC(Integer id) {
		getCurrentSession().createQuery("delete from EnergyManagementConfiguration where id = :id").
			setParameter("id", id).executeUpdate();
	}

	@Override
	public EnergyManagementConfiguration getEMCById(Integer id) {
		return (EnergyManagementConfiguration)getCurrentSession().
					createQuery("from EnergyManagementConfiguration where id = :id")
					.setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnergyManagementConfiguration> getAllEMC() {
		return (List<EnergyManagementConfiguration>)getCurrentSession().
				createQuery("from EnergyManagementConfiguration").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnergyManagementConfiguration> fuzzyQueryByName(String name) {
		return (List<EnergyManagementConfiguration>)getCurrentSession().
				createQuery("from EnergyManagementConfiguration where name like :name")
				.setParameter("name", name).list();
	}

}
