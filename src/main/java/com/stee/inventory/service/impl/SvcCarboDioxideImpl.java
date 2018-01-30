package com.stee.inventory.service.impl;

import com.stee.sel.common.ResultData;
import com.stee.inventory.entity.CarboDioxide;
import com.stee.inventory.repository.ICarboDioxideDao;
import com.stee.inventory.service.ISvcCarboDioxide;
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
 * File Name    : SvcCarboDioxideImpl.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:34:29
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
@Service("iSvcCarboDioxide")
public class SvcCarboDioxideImpl implements ISvcCarboDioxide{
	
	@Autowired@Qualifier("iCarboDioxideDao")
	private ICarboDioxideDao iCarboDioxideDao;
	
	@Override
	public ResultData save(CarboDioxide carboDioxide) {
		try{
			iCarboDioxideDao.save(carboDioxide);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData update(CarboDioxide carboDioxide) {
		try{
			iCarboDioxideDao.update(carboDioxide);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData deleteCD(Integer id) {
		try{
			iCarboDioxideDao.deleteCD(id);
			return ConfigUtil.ok();
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData getCDByName(String name) {
		try{
			return ConfigUtil.okOnlyOne(iCarboDioxideDao.getCDByName(name));
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData getAllCD() {
		try{
			return ConfigUtil.okList(iCarboDioxideDao.getAllCD());
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

	@Override
	public ResultData fuzzyQueryByName(String name) {
		try{
			StringBuffer sql = new StringBuffer("%").append(name).append("%");
			return ConfigUtil.okList(iCarboDioxideDao.fuzzyQueryByName(sql.toString()));
		}catch(Exception e){
			return ConfigUtil.fail();
		}
	}

}
