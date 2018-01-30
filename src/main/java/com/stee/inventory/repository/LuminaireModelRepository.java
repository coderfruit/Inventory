package com.stee.inventory.repository;

import com.stee.sel.asm.LuminaireModelConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
 * Project Name : STL_ASM
 * File Name    : LuminaireModelRepository.java
 * Author       : Jerry
 * Created      : 2016年11月15日 下午1:49:08
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface LuminaireModelRepository extends JpaRepository<LuminaireModelConfig, Integer>,JpaSpecificationExecutor<LuminaireModelConfig> {
	List<LuminaireModelConfig> findByModelIdLike(String modelId);
}
