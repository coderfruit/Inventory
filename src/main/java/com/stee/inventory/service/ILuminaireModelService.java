package com.stee.inventory.service;

import com.stee.inventory.entity.LuminaireQueryBean;
import com.stee.sel.asm.LuminaireModelConfig;
import com.stee.sel.common.ResultData;

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
 * File Name    : ILuminaireModelService.java
 * Author       : Jerry
 * Created      : 2016年11月15日 下午1:46:41
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface ILuminaireModelService {

	ResultData<LuminaireModelConfig> getAll();

	String save(LuminaireModelConfig config);

	boolean isIdExits(Integer id);

	String delete(Integer id);

	ResultData<LuminaireModelConfig> findByModelIdLike(LuminaireQueryBean query);

}
