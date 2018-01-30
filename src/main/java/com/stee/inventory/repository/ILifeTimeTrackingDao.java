package com.stee.inventory.repository;

import com.stee.inventory.entity.LifeTimeTracking;

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
 * File Name    : ILifeTimeTrackingDao.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:05:45
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public interface ILifeTimeTrackingDao {
	
	public void save(LifeTimeTracking lifeTimeTracking);
	
	public void update(LifeTimeTracking lifeTimeTracking);
	
	public void deleteLT(Integer id);
	
	public List<LifeTimeTracking> getLTById(Integer id);
	
	public List<LifeTimeTracking> getAllLT();
	
}
