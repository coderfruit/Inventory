package com.stee.inventory.service;

import com.stee.inventory.entity.QueryBean;
import com.stee.sel.asm.LifetimeTrackingConfig;
import org.springframework.data.domain.Page;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : STL_ASM
 * File Name    : ILifetimeTrackingService.java
 * Author       : Jerry
 * Created      : 2016年12月1日 上午10:31:46
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface ILifetimeTrackingService {

	String save(LifetimeTrackingConfig config);

	boolean isNameExits(String name);

	Page<LifetimeTrackingConfig> getByPage(QueryBean query, Integer pageNo, Integer pageSize, String direction);

	String deleteByName(Integer id);

}
