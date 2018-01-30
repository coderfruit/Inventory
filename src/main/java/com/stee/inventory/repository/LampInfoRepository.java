package com.stee.inventory.repository;

import com.stee.sel.lim.LampInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

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
 * Project Name : STL_LIM
 * File Name    : LampInfoRepository.java
 * Author       : Jerry
 * Created      : 2016年10月15日 上午11:01:42
 *
 * <p> History : <br><br>
 *
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */
public interface LampInfoRepository extends JpaRepository<LampInfo, String>,JpaSpecificationExecutor<LampInfo>,PagingAndSortingRepository<LampInfo, String> {
	List<LampInfo> findByGeoZoneId(String geoZoneId);
}
