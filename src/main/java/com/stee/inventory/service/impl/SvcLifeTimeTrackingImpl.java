package com.stee.inventory.service.impl;

import com.stee.sel.common.ResultData;
import com.stee.inventory.entity.LifeTimeTracking;
import com.stee.inventory.repository.ILifeTimeTrackingDao;
import com.stee.inventory.service.ISvcLifeTimeTracking;
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
 * File Name    : SvcLifeTimeTrackingImpl.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午4:09:51
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service("iSvcLifeTimeTracking")
public class SvcLifeTimeTrackingImpl implements ISvcLifeTimeTracking{
	
	@Autowired@Qualifier("iLifeTimeTrackingDao")
	private ILifeTimeTrackingDao iLifeTimeTrackingDao;
	
	@Override
	public ResultData save(LifeTimeTracking lifeTimeTracking) {
		try{
			iLifeTimeTrackingDao.save(lifeTimeTracking);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData update(LifeTimeTracking lifeTimeTracking) {
		try{
			iLifeTimeTrackingDao.update(lifeTimeTracking);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData deleteLT(Integer id) {
		try{
			iLifeTimeTrackingDao.deleteLT(id);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData getLTById(Integer id) {
		try{
			return ConfigUtil.okOnlyOne(iLifeTimeTrackingDao.getLTById(id));
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData getAllLT() {
		try{
			return ConfigUtil.okList(iLifeTimeTrackingDao.getAllLT());
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

}
