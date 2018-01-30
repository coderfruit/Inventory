package com.stee.inventory.service;

import com.stee.sel.common.ResultData;
import com.stee.inventory.entity.CarboDioxide;
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
 * File Name    : ISvcCarboDioxide.java
 * Author       : xiongxiaobo
 * Created      : 2016年11月29日 下午3:28:20
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public interface ISvcCarboDioxide {
	public ResultData save(CarboDioxide carboDioxide);
	
	public ResultData update(CarboDioxide carboDioxide);
	
	public ResultData deleteCD(Integer id);
	
	public ResultData getCDByName(String name);
	
	public ResultData getAllCD();
	
	public ResultData fuzzyQueryByName(String name);
}
