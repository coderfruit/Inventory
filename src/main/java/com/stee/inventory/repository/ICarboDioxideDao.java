package com.stee.inventory.repository;

import com.stee.inventory.entity.CarboDioxide;

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
 * File Name    : ICarboDioxideDao.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:05:20
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public interface ICarboDioxideDao {
	
	public void save(CarboDioxide carboDioxide);
	
	public void update(CarboDioxide carboDioxide);
	
	public void deleteCD(Integer id);
	
	public CarboDioxide getCDByName(String name);
	
	public List<CarboDioxide> getAllCD();
	
	public List<CarboDioxide> fuzzyQueryByName(String fuzzyName);
	
}
