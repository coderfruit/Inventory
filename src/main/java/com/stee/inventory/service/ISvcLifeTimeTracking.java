package com.stee.inventory.service;

import com.stee.sel.common.ResultData;
import com.stee.inventory.entity.LifeTimeTracking;
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
 * File Name    : ISvcLifeTimeTracking.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:31:38
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public interface ISvcLifeTimeTracking {
	public ResultData save(LifeTimeTracking lifeTimeTracking);
	
	public ResultData update(LifeTimeTracking lifeTimeTracking);
	
	public ResultData deleteLT(Integer id);
	
	public ResultData getLTById(Integer id);
	
	public ResultData getAllLT();
}
